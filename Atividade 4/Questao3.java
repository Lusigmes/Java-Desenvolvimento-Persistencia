/*
3. Crie uma aplicação em Java que recebe via linha de comando 
(1) o nome de um arquivo a ser decriptado e 
(2) o nome do arquivo resultante da decriptação e 
(3) a chave de decriptação.
 */

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.io.*;

public class Questao3 {
    static String IV = "ABCDEFGHIJKLMNOP";
    public static byte[] descriptar(String arquivo, String encriptado, String chave){
        try {
            FileInputStream arquivoEncriptado = new FileInputStream(arquivo);
            byte[] arquivoEncriptadoByte = new byte[arquivoEncriptado.available()];
                
            arquivoEncriptado.read(arquivoEncriptadoByte);
            arquivoEncriptado.close();
            
            Cipher decript = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE"); 
             
            SecretKeySpec key = new SecretKeySpec(chave.getBytes(/*"UTF-8"*/), "AES");
           
            decript.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes(/*UTF-8*/)));
                
            return decript.doFinal(arquivoEncriptadoByte);

       
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }  catch (Exception e) {
            e.printStackTrace();
        } 
        return null;
    }


    public static void main(String[] args) {
        String encriptadoIn = args[0];
        String descriptadoOut = args[1];
        String chave = args[2];
        byte[] arquivoDescriptado = descriptar(encriptadoIn, descriptadoOut, chave);

        try {
            FileOutputStream descriptadoSaida = new FileOutputStream(descriptadoOut);
            descriptadoSaida.write(arquivoDescriptado);
            descriptadoSaida.close();
                System.out.println("OK");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

/*$ $ java Questao3 "texto-encriptado.txt" "texto-descriptado.txt" "01a23b45c67d89e0" OK */