package br.estudo.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;

public class GenericJPADAO<T> implements GenericDAO<T> {
    protected Class<T> persistentClass;

        public GenericJPADAO(){}

        public GenericJPADAO(Class<T> persistentClass){
            this.persistentClass = persistentClass;
        }


   
    public void save(T entity) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        try {
            JPAUtil.beginTransaction();
            entityManager.merge(entity);
            JPAUtil.commit();
        } catch (Exception e) {
            JPAUtil.rollback();
            throw new DAOException("Não foi possivel salvar entity mensagem");
        }finally{
            JPAUtil.closeEntityManager();
        }
    }
    

    public void remover(T entity) {
        EntityManager entityManager = JPAUtil.getEntityManager();
          try {
            JPAUtil.beginTransaction();
            entityManager.remove(entityManager.merge(entity));
            JPAUtil.commit();
        } catch (Exception e) {
            JPAUtil.rollback();
            throw new DAOException("Não foi possivel remover entity mensagem");
        }finally{
            JPAUtil.closeEntityManager();
        }

    }

    public EntityManager gEntityManager(){
        return JPAUtil.getEntityManager();
    }

   
    public T find(Object id) {
        return gEntityManager().find(persistentClass, id);
    }


    public List<T> find() {
        CriteriaQuery<T> criteriaQuery = gEntityManager().getCriteriaBuilder().createQuery(persistentClass);
        criteriaQuery.from(persistentClass);
        return gEntityManager().createQuery(criteriaQuery).getResultList();    }

 
    public void beginTransaction() {
        JPAUtil.beginTransaction();
    }


    public void commit() {
        JPAUtil.commit();
    }

 
    public void rollback() {
        JPAUtil.rollback();
    }


    public void close() {
        JPAUtil.closeEntityManager();
    }
    
    

}
