package br.estudo.mensagemspring.dao;

import java.util.Date;
import java.util.List;

import br.estudo.mensagemspring.entity.Mensagem;

public interface MensagemDAO {
    public void save(Mensagem msg);
    public void remove(int id);
    public Mensagem find(int id);
    public List<Mensagem> find();
    public Mensagem findByCodigo(String codigo);
    public List<Mensagem> findByNome(String nome);
    public List<Mensagem> findByDatas(Date dataEntrada,Date dataSaida);
}