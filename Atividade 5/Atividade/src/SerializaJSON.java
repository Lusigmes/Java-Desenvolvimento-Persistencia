/*6. Crie uma classe Java de nome SerializaJSON
para instanciar objetos da classe definida na
Questão 1 e adicionar esses objetos em uma Lista.
Depois percorrer a lista e Serializar os objetos em disco/ssd.
Serialize usando a Serialização de objetos da biblioteca Jackson.
Ver também: Serialization and Deserialization
in Java using Jackson A practical guide on how to 
serialize and deserialize objects to JSON in Java using Jackson. */

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class SerializaJSON {
    public static void main(String[] args) {

        Usuario u1 = new Usuario(1,"User1","Endereco1",12345);
        Usuario u2 = new Usuario(2,"User2","Endereco2",54321);
               
        List<Usuario> usr = new ArrayList<Usuario>();
        Usuarios users = new Usuarios(usr);
        users.addUsuarios(u1);
        users.addUsuarios(u2);

        try {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            FileOutputStream saida = new FileOutputStream("SerializaJASON.json");

            objectMapper.writeValue(saida, users);
            saida.close();
            System.out.println("SerializaJASON");
        } catch(JsonProcessingException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
