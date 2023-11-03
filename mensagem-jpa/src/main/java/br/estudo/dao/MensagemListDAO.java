package br.estudo.dao;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import br.estudo.entity.Mensagem;

public class MensagemListDAO implements MensagemDAO{
    private List<Mensagem> mensagens;
    private static int idProximo = 1;

    public MensagemListDAO(){
        this.mensagens = new ArrayList<Mensagem>();
    }

    private int findIndex(int id){
        for(int i =0; i< mensagens.size(); i++){
            if(mensagens.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }

    public void save(Mensagem mensagem) {
        if(mensagem.getId() == 0){
            mensagem.setId(idProximo++);
            mensagens.add(mensagem);
        }else{
            int posLista = findIndex(mensagem.getId());
            mensagens.set(posLista, mensagem);
        }
    
    
    }

    @Override
    public void remove(int id) {
        mensagens.remove(find(id));
    }

    @Override
    public Mensagem find(int id) {
        for( Mensagem m : mensagens){
            if(m.getId() == id){
                return m;
            }
        }
        return null;
        
    }

    @Override
    public List<Mensagem> find() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'find'");
    }

    @Override
    public Mensagem findByCodigo(String codigo) {
      
        for( Mensagem m : mensagens){
            if(m.getCodigo().equals(codigo)){
                return m;
            }
        }
        return null;
    }

    @Override
    public List<Mensagem> findByNome(String str) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByNome'");
    }

    @Override
    public List<Mensagem> findByDescricao(String descricao) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByDescricao'");
    }

    @Override
    public List<Mensagem> findByDataEntrada(Date dataInicial, Date dataFinal) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByDataEntrada'");
    }
    
}
