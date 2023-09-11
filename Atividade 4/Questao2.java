/*

2. Crie uma aplicação em Java que recebe via linha de comando 
(1) o nome de um arquivo a ser encriptado, 
(2) o nome do arquivo encriptado a ser criado e 
(3) a chave de encriptação.
*/

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.io.*;

public class Questao2 {
    static String IV = "ABCDEFGHIJKLMNOP";
    public static byte[] encriptar(String arquivo, String encriptado, String chave){
        try {
            FileInputStream arquivoNormal = new FileInputStream(arquivo);
            byte[] arquivoByte = new byte[arquivoNormal.available()];
                //System.out.println(arquivoByte);
            arquivoNormal.read(arquivoByte);
            arquivoNormal.close();
            
            Cipher encript = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE"); 
                //System.out.println(encript);
            SecretKeySpec key = new SecretKeySpec(chave.getBytes(/*"UTF-8"*/), "AES");
                //System.out.println(key);
            encript.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes(/*UTF-8*/)));
                //System.out.println(encript);
            return encript.doFinal(arquivoByte);

       
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }   catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }   catch (NoSuchAlgorithmException e) {
            e.printStackTrace(); 
        }   catch (InvalidKeyException e) {
            e.printStackTrace();
        }   catch (NoSuchProviderException e) {
            e.printStackTrace();
        }   catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }  catch(InvalidAlgorithmParameterException e){
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        String arquivoIn = args[0];
        String encriptadoOut = args[1];
        String chave = args[2];
        byte[] arquivoEncriptado = encriptar(arquivoIn, encriptadoOut, chave);

        try {
            FileOutputStream encriptadoSaida = new FileOutputStream(encriptadoOut);
            encriptadoSaida.write(arquivoEncriptado);
            encriptadoSaida.close();
                System.out.println("OK");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

/* $ java Questao2 "texto.txt" "texto-encriptado.txt" "01a23b45c67d89e0" OK */