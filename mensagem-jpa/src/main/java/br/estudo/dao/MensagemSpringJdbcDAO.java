package br.estudo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import br.estudo.entity.Mensagem;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MensagemSpringJdbcDAO  implements MensagemDAO{
    private NamedParameterJdbcTemplate jdbcTemplate;

    public MensagemSpringJdbcDAO(){
        try {
            if(this.jdbcTemplate == null){
                this.jdbcTemplate = new NamedParameterJdbcTemplate(ConnectionFactory.getDataSource());
            }
        } catch (SQLException e) {
            throw new DAOException("Erro ao cadastrar mensagem no spring");
        }
    }
    
    private Mensagem map(ResultSet resultSet) throws SQLException{
        Mensagem msg = new Mensagem();
        msg.setId(resultSet.getInt("id"));
        msg.setNome(resultSet.getString("nome"));
        msg.setCodigo(resultSet.getString("codigo"));
        msg.setDescricao(resultSet.getString("descricao"));
        msg.setDataEntrada(resultSet.getDate("data_entrada"));
        return msg;
    }


    public void save(Mensagem mensagem){
        String save_sql ="insert into mensagem(nome, codigo, descricao, data_entrada) values(:nome, :codigo, :descricao, :data_entrada)";
        String update_sql ="update mensagem set nome = :nome, codigo=:codigo, descricao=:descricao, data_entrada=:data_entrada";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("nome", mensagem.getNome())
            .addValue("codigo", mensagem.getCodigo())
            .addValue("descricao", mensagem.getDescricao())
            .addValue("data_entrada", new Date());
        
        if(mensagem.getId() == 0 ){
            jdbcTemplate.update(save_sql, parameterSource);
        }else{
            parameterSource.addValue("id", mensagem.getId());
            jdbcTemplate.update(update_sql, parameterSource);
        }
    }

    @Override
    public void remove(int id) {
        String remove_sql ="delete from mensagem where id = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("id", id);
        jdbcTemplate.update(remove_sql, parameterSource);
    }

    @Override
    public Mensagem find(int id) {
        Mensagem mensagem = null;
        try {
            String find_sql = "select * from mensagem where id = :id";
            MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", id);
                
            mensagem = jdbcTemplate
                .queryForObject(find_sql, parameterSource, 
                                (msg, rowNum) -> map(msg));
        } catch (EmptyResultDataAccessException e) {
            log.debug(e.getMessage());
        }

        return mensagem;
    }

    @Override
    public List<Mensagem> find() {
        String findAll_sql = "select * from mensagem";
        return jdbcTemplate.query(findAll_sql, (msg , rowNum) -> map(msg));
    }

    @Override
    public Mensagem findByCodigo(String codigo) {
        Mensagem mensagem= null;
        try {
            String findCodigo_sql = "select * from mensagem where codigo = :codigo";
            MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("codigo", codigo);
            mensagem = jdbcTemplate.queryForObject(findCodigo_sql, parameterSource, (msg , rowNum) -> map(msg));
        } catch (EmptyResultDataAccessException e) {
            log.debug(e.getMessage());
        }

        return mensagem;
    }

    @Override
    public List<Mensagem> findByNome(String nome) {
        String findNome_sql = "select * from mensagem where upper(nome) like :nome";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("nome", "%" + nome.toUpperCase() + "%");
        return jdbcTemplate.query(findNome_sql, parameterSource, (msg, rowNum) -> map(msg));
        
    }

    @Override
    public List<Mensagem> findByDescricao(String descricao) {
        String findDescricao_sql = "select * from mensagem where upper(descricao) like upper(:descricao)";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("descricao", "%"+descricao+"%");
        return jdbcTemplate.query(findDescricao_sql, parameterSource, (msg, rowNum) -> map(msg));
    }

    @Override
    public List<Mensagem> findByDataEntrada(Date dataInicial, Date dataFinal) {
        String findData_sql = "select * from mensagem where data_entrada between :dataInicial and :dataFinal";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("dataInicial", new java.sql.Date(dataInicial.getTime()))
            .addValue("dataFinal", new java.sql.Date(dataFinal.getTime()));
        return jdbcTemplate.query(findData_sql, parameterSource, (msg,rowNum) -> map(msg));
    }

}
