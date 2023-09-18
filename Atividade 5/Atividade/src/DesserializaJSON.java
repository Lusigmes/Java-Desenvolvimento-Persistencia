/*7. Crie uma classe java de nome DesserializaJSON
para ler / desserializar os objetos Serializados
na Questão 6 e exibi-los. */

import java.io.FileInputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DesserializaJSON {
    public static void main(String[] args) {
        
        try {

            FileInputStream entrada = new FileInputStream("SerializaJASON.json");
            ObjectMapper objectMapper = new ObjectMapper();
            Usuarios users = objectMapper.readValue(entrada, Usuarios.class);

            entrada.close();
            System.out.println(users);
            System.out.println("DesserializaJSON");
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}
