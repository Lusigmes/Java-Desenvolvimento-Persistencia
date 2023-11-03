package br.estudo.dao;

import java.util.Date;
import java.util.List;

import br.estudo.entity.Mensagem;

public interface MensagemDAO {
    public void save(Mensagem mensagem);
	
	public void remove(int id);
	
	public Mensagem find(int id);
	
	public List<Mensagem> find();
	
	public Mensagem findByCodigo(String codigo);
	
	public List<Mensagem> findByNome(String str);

	public List<Mensagem> findByDescricao(String descricao);

	public List<Mensagem> findByDataEntrada(Date dataInicial, Date dataFinal);
}

