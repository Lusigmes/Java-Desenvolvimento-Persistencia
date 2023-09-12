import java.security.InvalidKeyException;

public class CriptRC4{
    private char[] key;
    private int[] sbox;
    private static final int SBOX_LENGHT = 256;
    private static final int TAM_MIN_CHAVE = 5;

    public void setChave(String chave) throws InvalidKeyException{
        if(!(chave.length() >= TAM_MIN_CHAVE && chave.length() < SBOX_LENGHT)){
            throw new InvalidKeyException("Tamanho da chave entre: "+ TAM_MIN_CHAVE +" e "+ (SBOX_LENGHT-1));
        }
        this.key = chave.toCharArray();
        //System.out.println(chave.toCharArray());
    }

    public CriptRC4(){}
    public CriptRC4(String chave) throws InvalidKeyException{
        setChave(chave);
        //System.out.println(this.key);
    }

    private void trocar(int i, int j, int[] sbox){
        int temp = sbox[i];
        sbox[i] = sbox[j];
        sbox[j] = temp;
    }
    
    private int[] initSbox(char[] key){
        int[] sbox = new int[SBOX_LENGHT];
        int j = 0;

        for(int i = 0; i < SBOX_LENGHT; i++){
            sbox[i] = i;
           //System.out.println(sbox[i]);
        }

        for(int i = 0; i < SBOX_LENGHT; i++){
            j = (j + sbox[i] + key[i % key.length]) % SBOX_LENGHT;
            //System.out.println(j);
            trocar(i, j, sbox);
        }

        return sbox;
    }

    public char[] criptografar(final char[] msg){
        char[] code = new char[msg.length];
        sbox = initSbox(key);
        int i = 0, j = 0;

        for(int k = 0; k < msg.length; k++){
            i = (i+1) % SBOX_LENGHT;
            j = (j+sbox[i]) % SBOX_LENGHT;
            trocar(i,j,sbox);

            int rand = sbox[(sbox[i] + sbox[j]) % SBOX_LENGHT];
            code[k] = (char)(rand ^ (int) msg[k]);
        }
        return code;
    }

    public char[] descriptografar(final char[] msg){
        return criptografar(msg);
    }

    public static void main(String[] args) {
        try {
            CriptRC4 rc4 = new CriptRC4("testekey");
            char[] textoCripto = rc4.criptografar("MENSAGEM MENSAGEM MENSAGEM TEXTO TEXTO TEXTO".toCharArray());
            System.out.println("Criptografado: "+ new String(textoCripto));
            System.out.println("Descriptografado: "+ new String(rc4.descriptografar(textoCripto)));
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }
}