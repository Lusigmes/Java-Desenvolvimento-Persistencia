import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class questao3 {
    public static void main(String[] args){
        String faiou1 = args[0];
        String faiou2 = args[1];

        try (BufferedReader reader1 = new BufferedReader(new FileReader(faiou1));
            BufferedReader reader2 = new BufferedReader(new FileReader(faiou2))){
                String line1, line2;

                //imprime uma linha de cada arquivo, intercalando file1 e file2
                //imprime as linhas intercaladas de file1 e file2, nesse caso file2 é menor, então quando file2 chega em EOF, o conteudo de file 1(que é maior) é parado.
                
                while( (line1 = reader1.readLine() ) != null && (line2 = reader2.readLine() ) != null ){
                    System.out.println(line1);
                    System.out.println(line2);
                }

                //imprime o conteudo do primeir arquivo e depois o conteudo do outro
                //se esses dois while estiverem descomentaos, ele termina apenas de imprimir o conteudo de file1(que não chegou em EOF)
                //file2 nao vai ser exibido pq chegou em EOF no primeiro while
                while( (line1 = reader1.readLine() ) != null  ){
                    System.out.println(line1);
                } 
                  while( (line2 = reader2.readLine() ) != null  ){
                System.out.println(line2);
                 }  
               
       
                reader1.close();
                reader2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


/* "\Users\Luis\Desktop\persAtv1\fileCatable1.txt"  "\Users\Luis\Desktop\persAtv1\fileCatable2.txt" */