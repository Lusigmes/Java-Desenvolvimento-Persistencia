package br.estudo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import br.estudo.entity.Mensagem;



public class MensagemJDBCDAO implements MensagemDAO{
    public MensagemJDBCDAO(){}


    public void save(Mensagem msg){
        Connection c = null;
        try {
            c = ConnectionFactory.getDataSource().getConnection();
            
            String inserir_sql = "insert into mensagem(nome,codigo,descricao, data_entrada) values(?, ?, ?, ?)";
            String update_sql = "update mensagem set nome = ?, codigo = ?, descricao = ?, data_entrada = ? where id = ?";

            PreparedStatement preparedStatement;

            if(msg.getId() == 0){
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
                if(c != null){
                    c.rollback();
                    throw new DAOException("Nova mensagem não cadastrada!",e);
                }
            } catch (SQLException ex) {
                throw new DAOException("Nova mensagem não cadastrada!",ex);
                
            }
        }finally{
            try {
                if(c != null){
                    c.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Nova mensagem não cadastrada!",e);
                
            }
        }
    }

    public void remove(int id){
        Connection c = null;
        try {
            c = ConnectionFactory.getConnection();
            c.setAutoCommit(false);
            String delete_sql = "delete from mensagem where id = ?";
            
            PreparedStatement preparedStatement = c.prepareStatement(delete_sql);
           
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            c.commit();
        } catch (SQLException e) {
             try {
                if(c != null){
                    c.rollback();
                    throw new DAOException("Nova mensagem não removida!",e);
                }
            } catch (SQLException ex) {
                throw new DAOException("Nova mensagem não removida!",ex);
                
            }
        }finally{
            try {
                if(c != null){
                    c.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Remoção não executada!",e);
            }
            
        }
    }
    
    public Mensagem find(int id){
        Connection c = null;
        Mensagem msg = null;
        
        try {
            c = ConnectionFactory.getConnection();
            c.setAutoCommit(false);

            String find_sql = "select id, nome, codigo, descricao, data_entrada from mensagem where id = ?";
            PreparedStatement preparedStatement = c.prepareStatement(find_sql);
            
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                msg = map(resultSet);
            }
            c.commit();
        } catch (SQLException e) {
           try {
                if(c != null){
                    c.rollback();
                    throw new DAOException("Nova mensagem não encontrada por ID!",e);
                }
            } catch (SQLException ex) {
                throw new DAOException("Nova mensagem não encontrada por ID!",ex);
                
            }
        }finally{
            try {
                if(c != null){
                    c.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Busca não executada!",e);
            }
        }
        
        
        return msg;
    }
    
    public List<Mensagem> find(){
        Connection c = null;
        List<Mensagem> listMensagem = null;
        
        try {
            c = ConnectionFactory.getConnection();
            c.setAutoCommit(false);
            String list_sql = " select id, nome, codigo, descricao, data_entrada from mensagem";
            PreparedStatement preparedStatement = c.prepareStatement(list_sql);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            listMensagem = new ArrayList<Mensagem>();

            while(resultSet.next()){
                Mensagem msg = map(resultSet);
                listMensagem.add(msg);
            }
        c.commit();
        
        } catch (SQLException e) {
           try {
                if(c != null){
                    c.rollback();
                    throw new DAOException("Nova mensagem não encontrada LISTA!",e);
                }
            } catch (SQLException ex) {
                throw new DAOException("Nova mensagem não encontrada LISTA!",ex);
                
            }        
        }finally{
            try {
                if(c != null){
                    c.close();
                }
            } catch (Exception e) {
                throw new DAOException("Listagem não executada!",e);        
            }
        }





        return listMensagem;
    }

    public Mensagem findByCodigo(String codigo){
        Connection c = null;
        Mensagem msg = null;
        try {
            c = ConnectionFactory.getConnection();
            c.setAutoCommit(false);
            String findCodigo_sql = "select id, nome, codigo, descricao, data_entrada from mensagem where codigo = ?";
            PreparedStatement preparedStatement = c.prepareStatement(findCodigo_sql);

            preparedStatement.setString(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();
           
            if(resultSet.next()){
                msg = map(resultSet);
            }
            c.commit();        
        } catch (SQLException e) {
            try {
                if(c != null){
                    c.rollback();
                    throw new DAOException("Nova mensagem não encontrada por codigo e!",e);
                }
            } catch (SQLException ex) {
                throw new DAOException("Nova mensagem não encontrada por codigo! ex",ex);
                
            }
        
        }finally{
            try {
                if(c != null) {
                    c.close();
                }
            } catch (SQLException e) {
            throw new DAOException("Busca por codigo não executada");
        
            }
        }

        return msg;
    }
    
    public List<Mensagem> findByNome(String nome){
        Connection c = null;
        List<Mensagem> listMensagem = null;

        try {
            c = ConnectionFactory.getConnection();
            c.setAutoCommit(false);
            String findNome_sql = "select id, nome, codigo, descricao from mensagem where upper(nome) like ?";
            PreparedStatement preparedStatement = c.prepareStatement(findNome_sql);
            preparedStatement.setString(1, "%" + nome.toUpperCase() + "%");
           
            ResultSet resultSet = preparedStatement.executeQuery();
            listMensagem = new ArrayList<Mensagem>();
           
            while(resultSet.next()){
                Mensagem msg = map(resultSet);
                listMensagem.add(msg);
            }
            c.commit();
        } catch (SQLException e) {
             try {
                if(c != null){
                    c.rollback();
                    throw new DAOException("Nova mensagem não encontrada por NOME!",e);
                }
            } catch (SQLException ex) {
                throw new DAOException("Nova mensagem não cadastrada por NOME!",ex);
                
            }
        }finally{
            try {
                if(c != null){
                    c.close();
                }
            }  catch (SQLException e) {
            throw new DAOException("Busca por nome não executada");
            }
        }
        return listMensagem;
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


    @Override
    public List<Mensagem> findByDescricao(String descricao) {
        Connection c = null;
        List<Mensagem> listMensagem = new ArrayList<Mensagem>();

        try {
            c = ConnectionFactory.getConnection();
            c.setAutoCommit(false);
            String findDescricao_sql = "select * from mensagem where UPPER(descricao) LIKE UPPER(?)";
            PreparedStatement preparedStatement = c.prepareStatement(findDescricao_sql);
            preparedStatement.setString(1, "%" + descricao + "%");
           
            ResultSet resultSet = preparedStatement.executeQuery();
           
            while(resultSet.next()){
                Mensagem msg = map(resultSet);
                listMensagem.add(msg);
            }
            c.commit();
        } catch (SQLException e) {
             try {
                if(c != null){
                    c.rollback();
                    throw new DAOException("Nova mensagem não encontrada por DESCRICAO!",e);
                }
            } catch (SQLException ex) {
                throw new DAOException("Nova mensagem não cadastrada por DESCRICAO!",ex);
                
            }
        }finally{
            try {
                if(c != null){
                    c.close();
                }
            }  catch (SQLException e) {
            throw new DAOException("Busca por DESCRICAO não executada");
            }
        }
        return listMensagem;    
    }

    @Override
    public List<Mensagem> findByDataEntrada(Date dataInicial, Date dataFinal) {
        Connection c= null;
        List<Mensagem> mensagems = new ArrayList<Mensagem>();

        try {
            c = ConnectionFactory.getConnection();
            c.setAutoCommit(false);
            String findData_sql = "select * from mensagem where data_entrada between ? and ?";
            PreparedStatement preparedStatement = c.prepareStatement(findData_sql);
            // new java.sql.Date(msg.getDataEntrada().getTime()
            preparedStatement.setDate(1, new java.sql.Date(dataInicial.getTime()));
            preparedStatement.setDate(2, new java.sql.Date(dataFinal.getTime()));

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //Mensagem m = map(resultSet); mensagems.add(m);
                mensagems.add(map(resultSet));
            }

            c.commit();
        } catch (SQLException e) {
             try {
                if(c != null){
                    c.rollback();
                    throw new DAOException("Nova mensagem não encontrada por datas!",e);
                }
            } catch (SQLException ex) {
                throw new DAOException("Nova mensagem não cadastrada por datas!",ex);
                
            }
        }finally{
            try {
                if(c != null){
                    c.close();
                }
            }  catch (SQLException e) {
            throw new DAOException("Busca por datas não executada");
            }
        }
        return mensagems;  
    }

}