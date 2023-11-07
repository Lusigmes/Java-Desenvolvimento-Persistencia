package br.estudo.mensagemspring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import br.estudo.mensagemspring.entity.Mensagem;
import lombok.extern.slf4j.Slf4j;

@Primary
@Repository
@Slf4j
public class MensagemSpringDAO  implements MensagemDAO {//spring jdbc dao

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public MensagemSpringDAO () {}
    
    private Mensagem map(ResultSet resultSet) throws SQLException{
        Mensagem msg = new Mensagem();
        msg.setId(resultSet.getInt("id"));
        msg.setNome(resultSet.getString("nome"));
        msg.setCodigo(resultSet.getString("codigo"));
        msg.setDescricao(resultSet.getString("descricao"));
        msg.setDataEntrada(resultSet.getDate("data_entrada"));
        return msg;
    }

    @Override
    public void save(Mensagem msg) {
        String insert_sql ="insert into mensagem(nome, codigo, descricao, data_entrada) values(:nome, :codigo, :descricao, :data_entrada)";
        String update_sql ="update mensagem set nome = :nome, codigo = :codigo, descricao = :descricao, data_entrada = :data_entrada where id = :id" ;
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("nome", msg.getNome())
            .addValue("codigo", msg.getCodigo())
            .addValue("descricao", msg.getDescricao())
            .addValue("data_entrada", new java.sql.Date(msg.getDataEntrada().getTime()));

        log.info("MENSAGEM ADICI0NADA");
        if(msg.getId() == 0){
            jdbcTemplate.update(insert_sql, parameterSource);
        }else{
            parameterSource.addValue("id", msg.getId());
            jdbcTemplate.update(update_sql, parameterSource);

        }
    }

    @Override
    public void remove(int id) {
        String remove_sql = "delete from mensagem where id = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("id", id);
        jdbcTemplate.update(remove_sql, parameterSource);

    }

    @Override
    public Mensagem find(int id) {
        Mensagem msg = null;
        try {
            String find_sql = "select * from mensagem where id = :id order by id asc";
            MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", id);
            
            msg = jdbcTemplate.queryForObject(find_sql, parameterSource, (msg_m, numRow)-> map(msg_m));
        } catch (EmptyResultDataAccessException e) {
            log.error("Erro: {}", e.getMessage(), e);
        }
        return msg;
    }


    @Override
    public List<Mensagem> find() {
        String findAll_sql = "select * from mensagem order by id asc";
        return jdbcTemplate.query(findAll_sql, (msg, numRow) -> map(msg));
    }

    @Override
    public Mensagem findByCodigo(String codigo) {
      Mensagem msg = null;
        try {
            String find_sql = "select * from mensagem where codigo = :codigo order by id asc";
            MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("codigo", codigo);
            
            msg = jdbcTemplate.queryForObject(find_sql, parameterSource, (msg_m, numRow)-> map(msg_m));
        } catch (EmptyResultDataAccessException e) {
            log.error("Erro: {}", e.getMessage(), e);
        }
        return msg;
    }

    @Override
    public List<Mensagem> findByNome(String nome) {
        String findAll_sql = "select * from mensagem where upper(nome) like upper(:nome)";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("nome", "%"+nome+"%");
        return jdbcTemplate.query(findAll_sql, parameterSource,(msg, numRow) -> map(msg));
    }

    @Override
    public List<Mensagem> findByDatas(Date dataEntrada, Date dataSaida) {
       String findAll_sql = "select * from mensagem where data_entrada between :dataEntrada and :dataSaida order by id asc";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("dataEntrada",new java.sql.Date(dataEntrada.getTime()))
            .addValue("dataSaida",new java.sql.Date(dataSaida.getTime()));
        return jdbcTemplate.query(findAll_sql, parameterSource,(msg, numRow) -> map(msg));
    } 
    
}
