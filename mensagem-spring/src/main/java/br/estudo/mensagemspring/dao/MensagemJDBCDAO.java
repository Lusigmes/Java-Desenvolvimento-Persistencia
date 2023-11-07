package br.estudo.mensagemspring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import br.estudo.mensagemspring.entity.Mensagem;

//  @Primary
@Repository
public class MensagemJDBCDAO implements MensagemDAO {

    @Autowired
    private DataSource dataSource;

    public void save(Mensagem msg){
        Connection c = null;

        try {
            c = dataSource.getConnection();
            c.setAutoCommit(false);

            String inserir_sql = "insert into mensagem(nome,codigo,descricao,data_entrada) values(?, ?, ?, ?)";
            String update_sql = "update mensagem set nome = ?, codigo = ?, descricao = ?, data_entrada = ? where id = ?";
            PreparedStatement preparedStatement;

            if(msg.getId() == 0) {
                preparedStatement = c.prepareStatement(inserir_sql);
            }else{
                preparedStatement = c.prepareStatement(update_sql);
                preparedStatement.setInt(5, msg.getId());
            }
            
            preparedStatement.setString(1, msg.getNome());
            preparedStatement.setString(2, msg.getCodigo());
            preparedStatement.setString(3, msg.getDescricao());
            preparedStatement.setDate(4, new java.sql.Date(msg.getDataEntrada().getTime()));
            
            preparedStatement.executeUpdate();
            c.commit();
        } catch (SQLException e) {
            try {
                if (c != null) {
                    c.rollback();
                    throw new DAOException("Erro ao cadastrar mensagem e");
                }
                
            } catch (SQLException ex) {
                throw new DAOException("Erro ao cadastrar mensagem ex",ex);
            }
        } finally{
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException ex) {
                throw new DAOException("Erro ao cadastrar mensagem ex2",ex);
            }

        }
    }
    
    public void remove(int id){
        Connection c = null;
        try {
            c = dataSource.getConnection();
            c.setAutoCommit(false);
            
            String delete_sql = "delete from mensagem where id = ?";
            PreparedStatement preparedStatement = c.prepareStatement(delete_sql);

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();


            c.commit();
        }  catch (SQLException e) {
            try {
                if (c != null) {
                    c.rollback();
                    throw new DAOException("Erro ao remover mensagem e");
                }
                
            } catch (SQLException ex) {
                throw new DAOException("Erro ao remover mensagem ex",ex);
            }
        } finally{
            try {
                if (c != null) {
                   c.close();
                }
            } catch (SQLException ex) {
                throw new DAOException("Erro ao remover mensagem ex2",ex);
            }
            
        }
    }

 
    public Mensagem find(int id){
        Mensagem mensagem = null;
        Connection c = null;
        try {
            c = dataSource.getConnection();
            c.setAutoCommit(false);

            String find_sql = "select * from mensagem where id = ?";
            PreparedStatement preparedStatement = c.prepareStatement(find_sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                mensagem = map(resultSet);           
            }



        }  catch (SQLException e) {
            try {
                if (c != null) {
                    c.rollback();
                    throw new DAOException("Erro ao buscar mensagem e");
                }
                
            } catch (SQLException ex) {
                throw new DAOException("Erro ao buscar mensagem ex",ex);
            }
        } finally{
            try {
                if (c != null) {
                   c.close();
                }
            } catch (SQLException ex) {
                throw new DAOException("Erro ao buscar mensagem ex2",ex);
            }

        }
        return mensagem;
    }

    public List<Mensagem> find(){
        Connection c = null;
        List<Mensagem> mensagems = new ArrayList<Mensagem>();
        try {
            c = dataSource.getConnection();
            c.setAutoCommit(false);
            String findAll_sql = "select * from mensagem order by id asc";
            PreparedStatement preparedStatement = c.prepareStatement(findAll_sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                mensagems.add(map(resultSet));
            }

            c.commit();
         }  catch (SQLException e) {
            try {
                if (c != null) {
                    c.rollback();
                    throw new DAOException("Erro ao buscar todas mensagens e");
                }
                
            } catch (SQLException ex) {
                throw new DAOException("Erro ao buscar todas mensagens ex",ex);
            }
        } finally{
            try {
                if (c != null) {
                   c.close();
                }
            } catch (SQLException ex) {
                throw new DAOException("Erro ao buscar todas mensagens ex2",ex);
            }

        }

        
        return mensagems;
    }


    public Mensagem findByCodigo(String codigo){
        Mensagem mensagem = null;
        Connection c= null;
        try {
            c = dataSource.getConnection();
            c.setAutoCommit(false);
            String findCodigo_sql = "select * from mensagem where codigo = ?";
            PreparedStatement preparedStatement = c.prepareStatement(findCodigo_sql);
            preparedStatement.setString(1, codigo);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                mensagem = map(resultSet);
            }

            c.commit();
        }  catch (SQLException e) {
            try {
                if (c != null) {
                    c.rollback();
                    throw new DAOException("Erro ao buscar mensagens por codigo e");
                }
                
            } catch (SQLException ex) {
                throw new DAOException("Erro ao buscar mensagens por codigo ex",ex);
            }
        } finally{
            try {
                if (c != null) {
                   c.close();
                }
            } catch (SQLException ex) {
                throw new DAOException("Erro ao buscar mensagens por codigo ex2",ex);
            }

            
        }
        return mensagem;
    }
   
   
    public List<Mensagem> findByNome(String nome){
        Connection c = null;
        List<Mensagem> mensagens = new ArrayList<Mensagem>();
        try {
            c = dataSource.getConnection();
            c.setAutoCommit(false);
            String findNome_sql = "select * from mensagem where upper(nome) like ?";
            PreparedStatement preparedStatement = c.prepareStatement(findNome_sql);
            preparedStatement.setString(1, "%"+ nome.toUpperCase() +"%");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Mensagem m = map(resultSet);
                mensagens.add(m);
            }


            c.commit();
           }  catch (SQLException e) {
            try {
                if (c != null) {
                    c.rollback();
                    throw new DAOException("Erro ao buscar mensagens por nome e");
                }
                
            } catch (SQLException ex) {
                throw new DAOException("Erro ao buscar mensagens por nome ex",ex);
            }
        } finally{
            try {
                if (c != null) {
                   c.close();
                }
            } catch (SQLException ex) {
                throw new DAOException("Erro ao buscar mensagens por nome ex2",ex);
            }

            
        }
        
        
        return mensagens;
    }

    public List<Mensagem> findByDatas(Date dataEntrada,Date dataSaida){
        Connection c = null;
        List<Mensagem> mensagens = new ArrayList<Mensagem>();
        try {
            c = dataSource.getConnection();
            c.setAutoCommit(false);
            String findNome_sql = "select * from mensagem where data_entrada between ? and ? order by id asc";
            PreparedStatement preparedStatement = c.prepareStatement(findNome_sql);
            preparedStatement.setDate(1, new java.sql.Date(dataEntrada.getTime()));
            preparedStatement.setDate(2, new java.sql.Date(dataSaida.getTime()));

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                mensagens.add(map(resultSet));
            }
            c.commit();
           }  catch (SQLException e) {
            try {
                if (c != null) {
                    c.rollback();
                    throw new DAOException("Erro ao buscar mensagem e");
                }
                
            } catch (SQLException ex) {
                throw new DAOException("Erro ao buscar mensagem ex",ex);
            }
        } finally{
            try {
                if (c != null) {
                   c.close();
                }
            } catch (SQLException ex) {
                throw new DAOException("Erro ao buscar mensagem ex2",ex);
            }

            
        }
        
        
        return mensagens;
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
    
}
