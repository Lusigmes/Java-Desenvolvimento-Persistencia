import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class questao2 {
    public static void main(String[] args){
        String fileIn = args[0];
        String fileOut = args[1];

        try(
            FileInputStream reader = new FileInputStream(fileIn);
            FileOutputStream writer = new FileOutputStream(fileOut);
        ){
            byte[] buffer = new byte[8192];
            int byteLido;

            long start = System.currentTimeMillis();
            /* long start = System.nanoTime(); */
            while((byteLido = reader.read(buffer)) != -1){
                writer.write(buffer, 0 , byteLido);
            }
            /* long end = System.nanoTime();
            System.out.println((end-start) + " ns"); */
            long end = System.currentTimeMillis();
            System.out.println(end-start + " ms");

        }catch(IOException e){
            e.printStackTrace();
        }


        /*
 * $ javac questao2.java
 * $ java questao2 "filePathable.txt" "exitfile2.txt"
 */



    }
}
