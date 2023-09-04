/* 
2. Escolha e baixe um arquivo csv a partir do link e use-o nas questões a seguir. 
Na resolução desta questão, não enviar os arquivos envolvidos, 
mas somente os resultados pedidos em arquivo texto com extensão txt.
Envie somente o link do arquivo CSV escolhido.
Gere as somas checksum, md5, sha1 e sha256 do arquivo csv escolhido,
usando utilitários de linha de comando do próprio sistema operacional ou instalado nele.
Compare os tempos de execução e tamanhos dos arquivos gerados. 
Dica: para obter o tempo de execução, use o comando time.

Exemplo: time md5sum iris.csv
*/

public class questao2{
    public static void main(String[] args) {
        /*!SECTION
         * https://www.kaggle.com/datasets/kritirathi/indian-food-dataset-with
         * 1.
         * $ md5sum "indiacomida.csv"
                46f4c5a6760f6f53e15a64eb31fff106 *indiacomida.csv
         * $ time md5sum "indiacomida.csv"
                46f4c5a6760f6f53e15a64eb31fff106 *indiacomida.csv

            real    0m0,171s
            user    0m0,030s
            sys     0m0,062s

         * 2.
         * $ sha1sum "indiacomida.csv"
                1c7b42727ab6653bd0183cc801f5d39dfca4ae37 *indiacomida.csv
         * $ time sha1sum "indiacomida.csv"
                1c7b42727ab6653bd0183cc801f5d39dfca4ae37 *indiacomida.csv

            real    0m0,434s
            user    0m0,000s
            sys     0m0,031s
         
         * 3.
         * $ sha256sum "indiacomida.csv"
                023ce684702c81998bb0b13927eac835b0075bc46080a7f69d31296aeb550d01 *indiacomida.csv
         * $ time sha256sum "indiacomida.csv"
                023ce684702c81998bb0b13927eac835b0075bc46080a7f69d31296aeb550d01 *indiacomida.csv

            user    0m0,000s
            sys     0m0,046s
            real    0m0,148s
         * 
         * 
         * 
        */
    }
}