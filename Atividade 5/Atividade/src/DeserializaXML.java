/*5. Crie uma classe java de nome DesserializaXML
para ler  desserializar os objetos Serializados
na Questão 4 e exibi-los. */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class DeserializaXML {
    public static void main(String[] args) {
        try {

            FileInputStream entrada = new FileInputStream("SerializaXML.xml");
            XmlMapper xmlMapper = new XmlMapper();
            Usuarios users = xmlMapper.readValue(entrada, Usuarios.class);
           
            entrada.close();
            System.out.println(users);
            System.out.println("DesserializaXMLjec");
        } catch( FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();

        }

    }
}
