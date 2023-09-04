
/*
1. Crie uma aplicação em Java que recebe via linha de comando: 
(1) o nome de um arquivo CSV;
(2) o delimitador usado para separar os campos do arquivo;
(3) uma lista de nomes das colunas do arquivo CSV que serão processados.

Considere que o arquivo CSV (1) deva ter um cabeçalho com os nomes das colunas em sua primeira
linha e que não tenha internamente colunas com Strings contendo o mesmo caractere usado como
delimitador (2). 
A aplicação deve exibir a soma e a média das colunas selecionadas em (3),
caso tenham dados numéricos. Se não tiverem dados numéricos, somente exibir que aquela coluna
não é numérica. 

Não usar bibliotecas externas para resolver esta questão (usar Java puro).

Sugere-se navegar apenas uma única vez em cada linha do arquivo CSV. 
Fazer a aplicação de modo que ela possa processar arquivos CSV extremamente grandes, mesmo que não caibam na memória RAM. 
Dica: usar o método split da classe String para separar os valores das colunas em cada linha do arquivo CSV.
https://www.kaggle.com/datasets/kritirathi/indian-food-dataset-with*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class questao1{
    public static void main(String[] args) throws FileNotFoundException{
        String csv = args[0];
        String delimitador = args[1]; 
        String[] colunas = new String[args.length - 2];

        System.arraycopy(args, 2, colunas, 0, colunas.length);
        
        try(    BufferedReader reader = new BufferedReader( new InputStreamReader(new FileInputStream(csv),"UTF-8"))    ){
           
            String linhaLida;
            Map<String, Double> somaCol = new HashMap<>();
            Map<String, Integer> contadorCol = new HashMap<>();
            Map<String, Integer> indicesColuna = new HashMap<>();

            //obtendo nomes da tabela
            linhaLida = reader.readLine();
            String[] nomeColunas = linhaLida.split(delimitador); 
            

            //mapear indices
            for(int i = 0; i < nomeColunas.length; i++){
               indicesColuna.put(nomeColunas[i], i);
            }
            Map<String, Boolean> errosPorColuna = new HashMap<>();//solucionar repetição de mensagem do catch para cada string contida na coluna

            while(( linhaLida = reader.readLine()) != null){
                String[] campos = linhaLida.split(delimitador);

                for(String coluna : colunas ){
               
                    int indiceColuna = indicesColuna.get(coluna);
                    String conteudo = campos[indiceColuna];                                            
                    /* System.out.println(conteudo);*/
      
                    if(conteudo != null && !conteudo.isEmpty()){
                        try{
                            double conteudoNum = Double.parseDouble(conteudo);
                            somaCol.put(coluna  , somaCol.getOrDefault(coluna, 0.0) + conteudoNum );
                            contadorCol.put(coluna  , contadorCol.getOrDefault(coluna, 0) + 1 );
                           /* System.out.println(conteudoNum);
                              System.out.println(somaCol);
                              System.out.println(contadorCol);*/
                            
                        }catch(NumberFormatException e){

                            if (!errosPorColuna.containsKey(coluna)) {//solucionar repetição de mensagem do catch para cada string contida na coluna
                                errosPorColuna.put(coluna, true);
                                System.out.println("Coluna não numérica: " + coluna);
                            }
                            break;    
                        } 
                    }
                }
            }

            for(String coluna : colunas){
                if(!errosPorColuna.containsKey(coluna)){
                    if(somaCol.containsKey(coluna)){
                        double soma = somaCol.get(coluna);
                        int contador = contadorCol.get(coluna);
                        double media = soma/contador;

                        System.out.println("Coluna: " + coluna);
                        System.out.println("Soma: " + String.format("%.2f", soma));
                        System.out.println("Media: " + String.format("%.3f", media));
                    }else
                        System.out.println("NAO OK");
                }
            }

         


            /* System.out.println(linhaLida = reader.readLine());
            for(String coluna : nomeColunas){
                System.out.println(coluna);
            } 
              for(String cols : nomeColunas){
                System.out.println(cols);
                if(cols.equals("coluna2")) break;
            } */
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}