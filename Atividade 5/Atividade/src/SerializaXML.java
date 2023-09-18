/*4. Crie uma classe Java de nome SerializaXML para instanciar
objetos da classe definida na Questão 1 e adicionar esses objetos
em uma Lista. Depois percorrer a lista e Serializar os objetos em disco/ssd.
Serialize usando a Serialização de objetos da biblioteca Jackson.
Ver também: XML Serialization and Deserialization with Jackson | Baeldung. */
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class SerializaXML {
    public static void main(String[] args) {

        Usuario u1 = new Usuario(1,"User1","Endereco1",12345);
        Usuario u2 = new Usuario(2,"User2","Endereco2",54321);
               
        List<Usuario> usr = new ArrayList<Usuario>();
        Usuarios users = new Usuarios(usr);
        users.addUsuarios(u1);
        users.addUsuarios(u2);


        try {
           
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            FileOutputStream saida = new FileOutputStream("SerializaXML.xml");


            xmlMapper.writeValue(saida, users);
            saida.close();
            System.out.println("SerializaXML");
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}
