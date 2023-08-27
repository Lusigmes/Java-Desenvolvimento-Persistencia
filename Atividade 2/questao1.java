import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class questao1{
    public static void main(String[] args) throws FileNotFoundException,IOException {
        String str = args[0];

        try{
            FileInputStream file = new FileInputStream(str);
            InputStreamReader stream = new InputStreamReader(file);
            BufferedReader reader = new BufferedReader(stream);
        
        String line;
        while ((line = reader.readLine()) != null){
            System.out.println(line);
        }
    }catch(IOException e){
        e.printStackTrace();
}
        
    }
}   