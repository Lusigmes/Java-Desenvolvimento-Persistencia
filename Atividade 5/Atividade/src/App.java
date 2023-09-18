import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;


import com.fasterxml.jackson.core.JsonProcessingException;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        List<Usuario> users = new ArrayList<Usuario>();
        Usuarios usuarios = new Usuarios(users);
        Usuario u1 = new Usuario(1,"User1","Endereco1",12345);
        Usuario u2 = new Usuario(2,"User2","Endereco2",54321);

        usuarios.addUsuarios(u1);
        usuarios.addUsuarios(u2);

        System.out.println(usuarios.toString());


        /* ObjectMapper om = new ObjectMapper();
        String s = om.writeValueAsString(usuarios); */



        String JASONstr = new ObjectMapper().writeValueAsString(usuarios);
        System.out.println(JASONstr);
        //assertTrue(JASONstr != null && !JASONstr.isEmpty());
    

    }
}
