/*1. Crie uma classe Java de entidade.
Escolha uma entidade que você já propôs para seu Trabalho Prático.
Exemplo: classe Filme (id, titulo, sinopse, diretor).
A classe deve implementar a interface java.io.Serializable.
Crie também uma classe que possua uma lista de objetos da entidade escolhida.
Exemplo: classe Filmes, possuindo uma lista de Filme (List<Filme> filmes).
Veja, nos slides sobre XML, os exemplos das classes Pessoa e Pessoas. */

import java.io.Serializable;

public class Usuario implements Serializable {
    private int id;
    private String nome;
    private String endereco;
    private transient int cpf;
    
  
  
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public int getCpf() {
        return cpf;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }
    
    public Usuario() {}

    public Usuario(int id, String nome, String endereco, int cpf) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nome=" + nome + ", endereco=" + endereco + ", cpf=" + cpf + "]";
    }

    public static void main(String[] args) {
        
    }
}
