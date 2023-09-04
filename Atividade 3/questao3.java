/*
3. Escreva um arquivo de propriedades Java via editor de textos. 
Esse arquivo deve ter os dados de chave e valor. Exemplo:
arquivo config.properties
arquivo = meu_arquivo.txt
linha_inicial = 1
linha_final = 3
Depois, escreva uma classe Java que exibe da linha_inicial
 até a linha_final do arquivo, conforme definidos no arquivo
 de propriedades config.properties.
 
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.util.Properties;

public class questao3{
    public static void main(String[] args) {
        /*  Properties config = new Properties();
        try {
            config.setProperty("arquivo", "meuarquivo.txt");
            config.setProperty("linha_inicial", "1");
            config.setProperty("linha_final", "5");
            config.store( new FileOutputStream("config.properties"), null);
        } catch (IOException e) {
            e.printStackTrace();
            
        } */
         Properties prop = new Properties();
        try {
            prop.load(questao3.class.getClassLoader().getResourceAsStream("config.properties"));

            /* System.out.println(prop.getProperty("arquivo"));
            System.out.println(prop.getProperty("linha_inicial"));
            System.out.println(prop.getProperty("linha_final")); */

            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(prop.getProperty("arquivo"))));
            String linhaLida;
            int contador = 0;

            int ini = Integer.parseInt(prop.getProperty("linha_inicial"));
            int fim = Integer.parseInt(prop.getProperty("linha_final"));

            while((linhaLida = reader.readLine()) != null){
                contador++;
                if(contador >= ini && contador <= fim){
                    System.out.println(linhaLida);
                    if(contador >= fim) break;
                }
            }
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
} 