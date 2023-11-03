package br.estudo.dao;

import java.util.Date;
import java.util.List;

import br.estudo.entity.Mensagem;
import jakarta.persistence.EntityManager;

public class MensagemJPADAO extends GenericJPADAO<Mensagem> implements MensagemDAO {

    public MensagemJPADAO(){
        super(Mensagem.class);
    }

    public void remove(int id) {
        remover(new Mensagem(id));
    }

   
    public Mensagem find(int id) {
        return find(Integer.valueOf(id));
    }

  
    public Mensagem findByCodigo(String codigo) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        Mensagem mensagem = entityManager.createQuery("select m from Mensagem m where m.codigo = :codigo", 
                Mensagem.class).setParameter("codigo", codigo).getSingleResult();
        JPAUtil.closeEntityManager();
        return mensagem;
    }
    
   
    public List<Mensagem> findByNome(String nome) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        List<Mensagem> mensagens = entityManager.createQuery("select c from Mensagem c where upper(c.nome) like upper(:nome)", 
            Mensagem.class).setParameter("nome", "%" + nome + "%").getResultList();
        JPAUtil.closeEntityManager();
       return mensagens;
    }

    public List<Mensagem> findByDescricao(String descricao) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        List<Mensagem> mensagens = entityManager.createQuery("select m from Mensagem m where UPPER(m.descricao) like UPPER(:descricao)", Mensagem.class)
            .setParameter("descricao", "%" + descricao + "%")
            .getResultList();
        JPAUtil.closeEntityManager();
        return mensagens;
    }

    public List<Mensagem> findByDataEntrada(Date dataInicial, Date dataFinal) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        
        List<Mensagem> mensagens = entityManager.createQuery(
            "SELECT m FROM Mensagem m WHERE m.dataEntrada BETWEEN :dataInicial AND :dataFinal",
            Mensagem.class
        )
        .setParameter("dataInicial", dataInicial)
        .setParameter("dataFinal", dataFinal)
        .getResultList();
        
        JPAUtil.closeEntityManager();
        
        return mensagens;
    }
    
}
