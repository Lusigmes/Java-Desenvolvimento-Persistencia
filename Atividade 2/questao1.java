import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class questao1{
    public static void main(String[] args) throws FileNotFoundException {
        String fileIn = args[0];
         String fileOut = args[1]; 
      /*    SEQUENCIA PARA ENTRANDA DE ARQUIVOS
            FileInputStream fileI = new FileInputStream(fileIn);
            InputStreamReader streamR = new InputStreamReader(file);
            BufferedReader reader = new BufferedReader(stream);
         

            SEQUENCIA PARA SAIDA DE ARQUIVOS
            FileOutputStream fileO = new FileOutputStream(fileOut);
            OutputStreamWriter streamW = new OutputStreamWriter(fileO);
            BufferedWriter writer = new BufferedWriter(streamW);
        
        mais chamadas de metodos menos desempenho   - eficiente para conversao de bytes para caracteres 
                                                    - ineficiente para arquivos muito grantes
        nao atende o enunciado a questao (rw byte a byte)
        mais eficiente para converter/copiar arquivos com codigicação iso para utf8(virse-verso)
        
        BufferedReader reader = new BufferedReader( new InputStreamReader(  new FileInputStream(fileIn)));
        BufferedWriter writer = new BufferedWriter( new OutputStreamWriter( new FileOutputStream(fileOut)));
       
        mais eficiente para copias simples, copiando os bytes brutos para o arquivo de saida
        sem necessidade de conversao de codificação do arquivo */
        try( FileInputStream reader = new FileInputStream(fileIn);
                FileOutputStream writer = new FileOutputStream(fileOut);
            ){
          
            int byteLido;
            
             /* long start = System.nanoTime(); */
             long start = System.currentTimeMillis(); 
            
            while( (byteLido = reader.read()) != -1){
                writer.write(byteLido);
            }
             long end = System.currentTimeMillis(); 
             /* long end = System.nanoTime(); */
 
            System.out.println((end-start) + " ms");
           /* System.out.println((end-start) + " ns"); */
 
        }catch(FileNotFoundException e){ //erro de busca
            e.printStackTrace();
         }catch(IOException e){     //erro de i/o
             e.printStackTrace();
        }
    }
}   

/*
 * $ javac questao1.java
 * $ java questao1 "filePathable.txt" "exitfile.txt"
 */