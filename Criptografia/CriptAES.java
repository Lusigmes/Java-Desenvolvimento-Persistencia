import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CriptAES {//256bits
    static String IV = "ABCDEFGHIJKLMNOP";//16 bytes
    static String TextoNormal = "TEXTO1TEXTO2TEXTO3TEXTO4TEXTO5TEXTO6TEXTO7TEXTO8TEXTO9TEXTO10";
    static String chaveCript = "01a23b45c67d89e0"; //128bits

    /* 

    public static byte[] encriptarAES(String texto, String chaveEncript) throws Exception{

        Cipher encriptar = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE" );
            System.out.println("Encriptar: "+ encriptar);
            System.out.println("Chave em bytes: "+ chaveEncript.getBytes("UTF-8"));
        SecretKeySpec chave = new SecretKeySpec(chaveEncript.getBytes("UTF-8"), "AES");
            System.out.println("Chave gerada: "+ chave);
        encriptar.init(Cipher.ENCRYPT_MODE, chave, new IvParameterSpec(IV.getBytes("UTF-8")));
            System.out.println("Encriptar iniciado: "+ encriptar);
        return encriptar.doFinal(texto.getBytes("UTF-8"));
    } 
    
    
    */
    public static byte[] encriptarAES(String texto, String chaveEncript) throws Exception{

        Cipher encriptar = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE" );
        SecretKeySpec chave = new SecretKeySpec(chaveEncript.getBytes("UTF-8"), "AES");
        
        encriptar.init(Cipher.ENCRYPT_MODE, chave, new IvParameterSpec(IV.getBytes("UTF-8")));
        
        return encriptar.doFinal(texto.getBytes("UTF-8"));
    }

    public static String descriptarAES(byte[] textoCriptografado, String chaveEncript) throws Exception{
        Cipher descriptar = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec chave = new SecretKeySpec(chaveEncript.getBytes("UTF-8"),"AES");
        
        descriptar.init(Cipher.DECRYPT_MODE, chave, new IvParameterSpec(IV.getBytes("UTF-8")));

        return new String(descriptar.doFinal(textoCriptografado),"UTF-8");
    }


    public static void main(String[] args) {
        try {

            System.out.println("Texto normal: "+ TextoNormal);
            //criptografar
            byte[] textoCriptografado = encriptarAES(TextoNormal, chaveCript);
            System.out.println("Texto criptografado: ");
            for(int i = 0; i < textoCriptografado.length; i++ ){
                System.out.print(Integer.valueOf(textoCriptografado[i]) + " ");
            }
            System.out.println();

            //descriptografar
            String textoDescriptografado = descriptarAES(textoCriptografado,chaveCript);
            System.out.println("Texto descriptografado: "+ textoDescriptografado);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }   
}
