import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class questao3 {
    public static void main(String[] args){
        String fileIn = args[0];
        String fileOut = args[1];

        try(
            BufferedReader reader = new BufferedReader( new InputStreamReader( 
                new FileInputStream(fileIn),"ISO-8859-1"));
            BufferedWriter writer = new BufferedWriter( new OutputStreamWriter( 
                new FileOutputStream(fileOut), "UTF-8"))
            ){
            String line;
            
            while((line = reader.readLine()) != null){
                writer.write(line);
                writer.newLine();
            }

            System.out.println("OK");

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
/* $ java questao3 "isofile.txt" "exitfile3.txt" */

/* Primeiro Try: Conversão de Bytes para Caracteres

BufferedReader lê bytes do arquivo e converte para caracteres.
BufferedWriter recebe caracteres e escreve bytes no arquivo.
Isso é útil para operações em texto, onde você precisa interpretar os bytes como caracteres.
Segundo Try: Leitura e Escrita de Bytes Brutos

FileInputStream lê bytes brutos do arquivo.
FileOutputStream escreve bytes brutos no arquivo.
Útil quando você quer trabalhar diretamente com os bytes, como em arquivos binários.
Ambos os métodos têm diferentes abordagens para trabalhar com arquivos, dependendo se
 você precisa manipular caracteres ou bytes brutos. */