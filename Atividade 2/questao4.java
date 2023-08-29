import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
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
            
                try(BufferedWriter writer = new BufferedWriter( new OutputStreamWriter( 
                    new FileOutputStream(fileOut), "ISO-8859-1"))){
                    writer.write(text.toString());
                    System.out.println("Salvo em "+ fileOut);
                }catch(IOException e){
                    e.printStackTrace();
                }
        }
    }
}

        
/* 
        Quando se trata apenas de copiar arquivos de um local para outro, 
        seja byte a byte ou em blocos de bytes, você pode usar diretamente 
        InputStream e OutputStream para a cópia. Não é necessário envolver 
        BufferedReader, BufferedWriter, InputStreamReader ou OutputStreamWriter
        nesse caso, pois essas classes estão mais relacionadas à manipulação 
        de caracteres e conversão de bytes para caracteres.

        Entretanto, se você precisa manipular caracteres ou converter entre
        diferentes conjuntos de caracteres (codificações), como no caso de
        ler texto de um arquivo com uma codificação específica e escrevê-lo
        com outra, então é quando você deve usar BufferedReader, BufferedWriter,
        InputStreamReader e OutputStreamWriter para garantir que as conversões
        de caracteres ocorram corretamente.

Resumindo:

Para cópia simples de bytes, use FileInputStream e FileOutputStream.

Para manipulação ou conversão de caracteres, use BufferedReader, BufferedWriter,
InputStreamReader e OutputStreamWriter.

*/