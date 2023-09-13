import java.util.Random;

public class CriptOTP {

    public String chaveGenerate(int tamanho){
        Random rand = new Random();
        char[] chave = new char[tamanho];

        for( int i = 0; i< tamanho; i++){
            chave[i] = (char) rand.nextInt(132);
            if((int) chave[i] < 97)
                chave[i] = (char)(chave[i] + 72);
            if((int) chave[i] > 122)
                chave[i] = (char)(chave[i] - 72);
        }

        return new String(chave);
    }

    private int charToInt(char c){
        return (int) c;
    }
    private char intToChar(int i){
        return (char) i;
    }
   
    private void erro(String msg){
            System.out.println(msg);
            System.out.println(-1);
    }
    
    private int[] arrayCTI(char[] cc ){
            int[] ii = new int[cc.length];
            for(int i=0; i < ii.length; i++){
                ii[i] = charToInt(cc[i]);
            }
            return ii;
    }
    private char[] arrayITC(int[] ii ){
            char[] cc = new char[ii.length];
            for(int i=0; i < ii.length; i++){
                cc[i] = intToChar(ii[i]);
            }
            return cc;
    }
   
    public String criptografar(String msg, String chave){
        if(msg.length() != chave.length()){
            erro("tamanho da mensagem e chave tem que ser igauis");
        }
        int[] iM = arrayCTI(msg.toCharArray());
        int[] iK = arrayCTI(chave.toCharArray());
        int[] data = new int[msg.length()];
        
        for(int i = 0; i < msg.length(); i++){
            data[i] = iM[i] + iK[i];
        }
        
        return new String(arrayITC(data));
    }
    public String decriptografar(String msgCript, String chave){
        if(msgCript.length() != chave.length()){
            erro("tamanho da mensagem e chave tem que ser igauis");
        }
        int[] iM = arrayCTI(msgCript.toCharArray());
        int[] iK = arrayCTI(chave.toCharArray());
        int[] data = new int[msgCript.length()];
    
        for(int i = 0; i < msgCript.length(); i++){
            data[i] = iM[i] - iK[i];
        }
        return new String(arrayITC(data));
    }

    public CriptOTP(){}

   
   
    public static void main(String[] args) {
        CriptOTP opt = new CriptOTP();
        
        String msg = "TESTANDO CRIPTOGRAFIA OTP";
        String  chave = opt.chaveGenerate(msg.length());

        String msgCript = opt.criptografar(msg, chave);
        String msgDecript = opt.decriptografar(msgCript, chave);

        System.out.println("Msg: "+ msg);
        System.out.println("Chave: "+ chave);
        System.out.println("Msg Criptografada: "+ msgCript);
        System.out.println("Msg Decriptografada: "+ msgDecript);

    }
}
