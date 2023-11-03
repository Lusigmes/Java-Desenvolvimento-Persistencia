package br.estudo.dao;

import java.util.Properties;

import br.estudo.Config;
import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JPAUtil {
    private static final EntityManagerFactory entityMF;

    private static ThreadLocal<EntityManager> threadLocal = new ThreadLocal<EntityManager>();

    static{
        Properties props = Config.getConfig();
        String persistenceUnit = props.getProperty("persistence.unit");
        log.info("persistenceUnit: {}", persistenceUnit);
        entityMF = Persistence.createEntityManagerFactory(persistenceUnit);
    }

    public static EntityManager getEntityManager(){
        EntityManager entityManager = threadLocal.get();
        if(entityManager == null){
            entityManager = entityMF.createEntityManager();
            threadLocal.set(entityManager);
        }
        return entityManager;
    }
    //ems = threadLcoal
    //em = entityManager emf= entityMF

    public static void closeEntityManager(){
        EntityManager entityManager = threadLocal.get();
        if(entityManager == null){
            EntityTransaction entityTransaction = entityManager.getTransaction();
            if(entityTransaction.isActive()){
                entityTransaction.commit();
            }
            entityManager.close();
            threadLocal.set(null);
        }
    }

    public static void beginTransaction(){
        getEntityManager().getTransaction().begin();
    }

    public static void commit(){
        EntityTransaction entityTransaction = getEntityManager().getTransaction();
        if(entityTransaction.isActive()){
            entityTransaction.commit();
        }
    }
    public static void rollback(){
        EntityTransaction entityTransaction = getEntityManager().getTransaction();
        if(entityTransaction.isActive()){
            entityTransaction.rollback();
        }
    }

}
