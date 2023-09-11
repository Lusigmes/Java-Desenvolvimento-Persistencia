/*
1. Crie uma aplicação em Java que recebe via linha de comando (1)
o nome de um arquivo compactado a ser criado e (2) uma pasta.
Compactar todos os arquivos e subpastas em um arquivo compactado com extensão zip.
 */
 // java.io.* vale a pena?
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Questao1{
    public static void zipar(ZipOutputStream zipSaida, File pasta, String caminho){
        try {
            //veriicando se é pasta
            if(pasta.isDirectory()){
                if(!caminho.isEmpty()){//verificando se o caminho nao é nulo
                    if(!caminho.endsWith("/")){ // garantir os arquivos dentro do zip tenham um caminho consistente
                        caminho += "/"; //garantindo a consistencia dos caminho
                    }
                    zipSaida.putNextEntry(new ZipEntry(caminho));
                    /*adicionando uma nova entrada ZIP para representar a pasta atual dentro do zip. */
                }

                 //listando o conteudo da pasta, seus arquivos e subpastas
                File[] arquivos =  pasta.listFiles();
                for(File arquivo : arquivos){
                //  System.out.println(arquivo);
                  zipar(zipSaida, arquivo, caminho + arquivo.getName()); //a cereja do bolo
                }

                System.out.println("A Pasta " +pasta+ " está compactado dentro de " +zipSaida);

            }else{
                //lendo arquivo normal ja que num é pasta e realizando leitura/escrita byte a byte do arquivo
                FileInputStream notPasta = new FileInputStream(pasta);

                zipSaida.putNextEntry(new ZipEntry(caminho));

                byte[] escrita = new byte[1024];
                int byteLido;

                while((byteLido = notPasta.read(escrita)) > 0){
                    zipSaida.write(escrita, 0, byteLido);
                }

                notPasta.close();
                System.out.println("O arquivo "+ pasta +" está compactado dentro de " +zipSaida);
            }
           
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
       // Questao1 sistema = new Questao1();
        String zip = args[0];
        String pasta = args[1];
        try {
            ZipOutputStream zipSaida = new ZipOutputStream(new FileOutputStream(zip)); //configurando zip de saida
            File pastaEntrada = new File(pasta); //configurando pasta de entrada recebida em arquivo
            /*sistema.*/zipar(zipSaida,pastaEntrada,"");
            zipSaida.finish();
            zipSaida.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}