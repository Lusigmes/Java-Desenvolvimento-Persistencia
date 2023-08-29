import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class questao4 {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){

            StringBuilder text = new StringBuilder();

            System.out.println("Escreva...");
            while(true){
                String line = sc.nextLine();

                if(line.equalsIgnoreCase("FIM")){
                    break;
                }
                text.append(line).append("\n");

            }
                System.out.println("Escreva o arquivo de saida");
                
                String fileOut = sc.nextLine();
            
                try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream(fileOut), "ISO-8859-1"))){

                    writer.write(text.toString());
                    System.out.println("Salvo em "+ fileOut);
                    
                }catch(IOException e){
                    e.printStackTrace();
                }



        }
    }
}
