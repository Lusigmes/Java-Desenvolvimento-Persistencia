/*1. Crie uma classe Java de entidade.
Escolha uma entidade que você já propôs para seu Trabalho Prático.
Exemplo: classe Filme (id, titulo, sinopse, diretor).
A classe deve implementar a interface java.io.Serializable.
Crie também uma classe que possua uma lista de objetos da entidade escolhida.
Exemplo: classe Filmes, possuindo uma lista de Filme (List<Filme> filmes).
Veja, nos slides sobre XML, os exemplos das classes Pessoa e Pessoas. */

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "user_master")
public class Usuarios implements Serializable{
    List<Usuario> usuarios;

    @JacksonXmlElementWrapper(localName = "usuarios")
    @JacksonXmlProperty(localName = "usuario")
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }


    public Usuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    public Usuarios() {}

    @Override
    public String toString() {
        return "Usuarios [usuarios=" + usuarios + "]";
    }

    public void addUsuarios(Usuario usuario){
        usuarios.add(usuario);
    }

    public void delUsuarios(int id){
        for(Usuario user : usuarios){
            if(user.getId() == id){
                usuarios.remove(user);
                break;
            }
        }
    }

    
}
