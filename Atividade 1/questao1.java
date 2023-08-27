import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class questao1 {
    public static void main(String[] args) throws FileNotFoundException{
        String file = args[0];
 
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            int count = 0;
            String line;
            while((line = reader.readLine()) != null && (count < 10)){
                System.out.println(line);
                count++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
/* "\Users\Luis\Desktop\persAtv1\filePathable.txt" */