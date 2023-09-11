/*
4. Crie uma aplicação em Java que recebe via linha de comando
o nome de um arquivo para geração/armazenamento dos hashes  md5, sha1 e sha256 do arquivo especificado.
A aplicação deve mostrar o tempo de execução de cada uma dessas operações. 
Dica: veja o seguinte tutorial: MD5 Hashing in Java | Baeldung
 */
import java.io.*;
import java.security.*;
import java.util.Base64;

import javax.crypto.*;


public class Questao4 {
    public static String verHash(String arquivo, String algoritmo){
        try {
            FileInputStream arquivoVerificar = new FileInputStream(arquivo);
            MessageDigest digest = MessageDigest.getInstance(algoritmo);
            System.out.println("\n"+ digest);
            
            byte[] buffer = new byte[8192];
            int byteLido;
            while((byteLido = arquivoVerificar.read(buffer)) != -1){
                digest.update(buffer, 0, byteLido);
            }

            arquivoVerificar.close();
            byte[] hashByte = digest.digest();
                //System.out.println(hashByte);
                //System.out.println(Base64.getEncoder().encodeToString(hashByte));
            return Base64.getEncoder().encodeToString(hashByte);

        }catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
    public static void main(String[] args) {
        String arquivo = args[0];

        long md5Ini = System.currentTimeMillis();
        String hashMD5 = verHash(arquivo, "MD5");
        long md5Fim = System.currentTimeMillis();
        System.out.println("\tChave MD5: "+ hashMD5 +"\n\tTempo: "+ (md5Ini-md5Fim));
        
        long sha1Ini = System.currentTimeMillis();
        String hashSHA1 = verHash(arquivo, "SHA1");
        long sha1Fim = System.currentTimeMillis();
        System.out.println("\tChave SHA1: "+ hashSHA1 +"\n\tTempo: "+ (sha1Ini-sha1Fim));
        
        long sha256Ini = System.currentTimeMillis();
        String hashSHA256 = verHash(arquivo, "SHA256");
        long sha256Fim = System.currentTimeMillis();
        System.out.println("\tChave SHA256: "+ hashSHA256 +"\n\tTempo: "+ (sha256Ini-sha256Fim));
    }
}