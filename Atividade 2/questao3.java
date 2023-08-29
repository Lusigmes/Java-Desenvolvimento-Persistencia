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
                 new FileInputStream(fileIn), "ISO-8859-1"));
            BufferedWriter writer = new BufferedWriter( new OutputStreamWriter(
                 new FileOutputStream(fileOut), "UTF-8"));
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