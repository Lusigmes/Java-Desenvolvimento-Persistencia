import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.*;

public class CriptDES{//120bits
    public static void main(String[] args) {
        try {
            KeyGenerator chaveGen = KeyGenerator.getInstance("DES");
            SecretKey chaveDES = chaveGen.generateKey();
                System.out.println("ChaveGeneration:"+ chaveGen);
                System.out.println("ChaveDES p encript: "+ chaveDES);

            Cipher cifDES;// criando e iniciando a crifra para a encriptação crifra a cifra
            cifDES = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cifDES.init(Cipher.ENCRYPT_MODE, chaveDES);
                System.out.println("CifraDES: "+ cifDES);

            byte[] textoNormal = "TEXTO TEXTO TEXTO TEXTO TEXTO TEXTO TEXTO TEXTOTEXTO TEXTOTEXTOTEXTO".getBytes();

                System.out.println("Texto b-b: "+ textoNormal);
                System.out.println("Texto normal: "+ new String(textoNormal));
            
            byte[] textoCriptografado = cifDES.doFinal(textoNormal);
            
                System.out.println("Texto encriptado: " + textoCriptografado);
            
                //iniciar a cifra também para para descriptação
             cifDES.init(Cipher.DECRYPT_MODE, chaveDES);
                System.out.println("CifraDES p descript: "+ cifDES);

            byte[] textoDescriptografado = cifDES.doFinal(textoCriptografado);
                System.out.println("Texto descriptografado: " + new String(textoDescriptografado));
            
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
    }   
}
  

/* cripto assimetrica
    a chave de cifragem é diferente da chave de decifragem, porem
    a de decifragem é baseada na de cifragem, sendo facilmente deduzivel.
   cripto simetrica -  IDEA, TwoFish, BlowFish, Serpent, DES, AES, RC5, RC6
    chave de cifragem é igual a de decifragem. A tranformação é dada bit
    a bit ou caractere por caractere, a execução é mais rapida.
*/
