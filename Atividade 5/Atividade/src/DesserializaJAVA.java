/*
 * 3. Crie uma classe java de nome DesserializaJava
 * para ler / desserializar os objetos Serializados na Questão 2 e exibi-los. 
*/
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
public class DesserializaJAVA {

    public static void main(String[] args) {

        try {

            FileInputStream entrada = new FileInputStream("SerializaJava.txt");
            ObjectInputStream entradaObjeto = new ObjectInputStream(entrada);
            Usuarios users = (Usuarios) entradaObjeto.readObject();
            System.out.println(users);


            entrada.close();
            entradaObjeto.close();
            System.out.println("DesserializaJAVA");
        }catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }  
    }
}
