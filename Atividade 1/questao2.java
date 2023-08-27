import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class questao2 {
    public static void main(String[] args){
        String file = args[0];
        String substring = args[1];
        
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null){
                if(line.contains(substring)){
                    System.out.println(line);
                }
            }
            reader.close();
        } catch (IOException e) {
         e.printStackTrace();
        }

    }
}

/* "\Users\Luis\Desktop\persAtv1\fileGrepable.txt" */