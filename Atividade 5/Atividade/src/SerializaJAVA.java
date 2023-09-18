/*2. Crie uma classe Java de nome SerializaJava
para instanciar objetos da classe definida na
Questão 1 e adicionar esses objetos em uma Lista.
Depois percorrer a lista e Serializar os objetos em disco/ssd.
Serialize usando a Serialização de objetos da própria API Java.
Ver também: Introduction to Java Serialization | Baeldung. */
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SerializaJAVA {
        public static void main(String[] args) throws Exception {

                Usuario u1 = new Usuario(1,"User1","Endereco1",12345);
                Usuario u2 = new Usuario(2,"User2","Endereco2",54321);
               
                List<Usuario> users = new ArrayList<Usuario>();
                Usuarios usuarios = new Usuarios(users);
                usuarios.addUsuarios(u1);
                usuarios.addUsuarios(u2);
             
                try {

                        FileOutputStream saida = new FileOutputStream("SerializaJava.txt");
                        ObjectOutputStream saidaObjeto = new ObjectOutputStream(saida);
                        saidaObjeto.writeObject(usuarios);

                        saida.close();
                        saidaObjeto.close();
                        saidaObjeto.flush();
                        System.out.println("SerializaJAVA");
                } catch (IOException e) {                        
                        e.printStackTrace();
                }
            
        }
}
