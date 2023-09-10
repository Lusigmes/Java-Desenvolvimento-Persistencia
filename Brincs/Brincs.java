import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class Brincs{
    /*!SECTION 1 - login */
    
/*     public void createProp(String name){
       Properties config = new Properties();
        try{
            config.setProperty("login","marcopaulo");
            config.setProperty("password","nasa");
            config.setProperty("keyIn","a1bx20");
            config.store(new FileOutputStream(name+".properties"), null);
        }catch(IOException e){
            e.printStackTrace();
        }
    } 
            sistema.createProp("nova"); // criar um arquivo name.properties , definir propriedades quando desejar
     */
    private Map<String, String> bancoAcc = new HashMap<>();

    public Brincs(){
         bancoAcc.put("admin","admin");   
         bancoAcc.put("luis","1234");   
         bancoAcc.put("user","user");   
         bancoAcc.put("","");   
    }

    public Boolean Autenticar(String login, String psw){ //autenticar o acesso pelo login, se estiver cadsatrado no brinncs.bancoAcc
        String password = bancoAcc.get(login);
       if(password == null){
           System.out.println("\nAcesso Negado!\nLogin não cadastrado!");
            return false;
       }else if(password != null && password.equals(psw)){
           System.out.println("\nAcesso Permitido!\nAproveite o sistema.\n");
           return true;
        }else{
            System.out.println("\nAcesso Negado!\nSenha incorreta!");
            return false;
        }
    }

    public Properties ReadProp(String cmd ) { // lê o arquivo name.properties passado pelo cmd e retorna as propriedades desejadas passadas pelo parametro quando a função for chamada com o .getProperties
         Properties propRead = new Properties();
         try {
            propRead.load(Brincs.class.getResourceAsStream(cmd));
            return propRead;
        } catch (IOException e) {
             e.printStackTrace();
        } 
        return propRead;
    }

    /*!SECTION 2 - SISTEMA */

    /*  LISTA DE CONTATOS PROVISORIO PARA TESTAR O SISTEMAMENSAGEM QUE É A FUNÇÃO PRINCIPAL */
    public static class ContatosMockados{
        public String nome;
        public int numero;
        public List<String> mensagens;

        public ContatosMockados(String nome, int numero){
            this.nome = nome;
            this.numero = numero;
            this.mensagens = new ArrayList<>();
        }
    }

    public static ContatosMockados[] criarLista(){
        ContatosMockados[] listaContatos = new ContatosMockados[10];

        /*listaContatos[0] = new ContatosMockados("Usuario 1",123);
        listaContatos[1] = new ContatosMockados("Usuario 2",321);
        listaContatos[2] = new ContatosMockados("Usuario 3",213);
        listaContatos[3] = new ContatosMockados("Usuario 4",322);
        listaContatos[4] = new ContatosMockados("Usuario 5",421);
        listaContatos[5] = new ContatosMockados("Usuario 6",143);
        listaContatos[6] = new ContatosMockados("Usuario 7",555);
        listaContatos[7] = new ContatosMockados("Usuario 8",551);
        listaContatos[8] = new ContatosMockados("Usuario 9",666);
        listaContatos[9] = new ContatosMockados("Usuario 10",212); */
        

        return listaContatos;
    }

    /* SISTEMA PRINCIPAL - APENAS UMA FUNÇÃO VOID, TEM QUE SERE CHAMADA PELA sistema DO Brincs */
    public void SistemaMensagem(Boolean loginAutenticado){
        ContatosMockados[] contatosLista = criarLista();
        int quantidade_contatos = 0; //max 10
        Scanner ler = new Scanner(System.in);
      
        if(loginAutenticado){

            Boolean euprecisodecomida = true;
            while(euprecisodecomida){
                
                System.out.println("\n|******************************************|\n");
                System.out.println("Seja bem-vindo ao sistema de mensagens");
                System.out.println("(1) Ver lista de contatos");
                System.out.println("(2) Adicionar um contato");
                System.out.println("(3) Remover um contato");
                System.out.println("(4) Enviar uma mensagem para um contato");
                System.out.println("(5) Buscar um contato");
                System.out.println("(6) Atualizar um contato");
                System.out.println("(0) Sair");
                System.out.println("Escolha uma opção: ");
                int escolha = ler.nextInt();
                System.out.println("|******************************************|\n");

                switch (escolha) {
                    
                    case 1 ://listar
                        System.out.println("\nLista de Contatos");
                        if(quantidade_contatos == 0){
                            System.out.println("\nLista vazia");
                        }else{
                            for(int i = 0; i < contatosLista.length; i++){
                                if(contatosLista[i] != null){
                                    System.out.println("|*******"+" Contato: "+ (i+1) +" *******|");
                                    System.out.println("     Nome:"+ contatosLista[i].nome);
                                    System.out.println("     Telefone:"+ contatosLista[i].numero);
                                    System.out.println("|***************************|\n");
                                }
                            } 
                        }
                        break;
           
                    case 2 ://add
                        if(quantidade_contatos < contatosLista.length){
                            System.out.println("Adicione um contato");
                            System.out.println("Dgite o nome: ");
                            String nome = ler.next();
                            System.out.println("Digite o telefone: ");
                            int telefone = ler.nextInt();

                            contatosLista[quantidade_contatos] = new ContatosMockados(nome, telefone);
                            quantidade_contatos++;
                            System.out.println("\n"+ nome +" adicionado com sucesso!");
                        }else{
                            System.out.println("Você atingiu o limite de contatos no pacote normal.\n");
                        }
                        break;
                        
                    case 3://deletar
                        System.out.println("Digite o nome do contato a remover: ");
                        String nomeRemove = ler.next();
                        Boolean removing = false;
                        
                        for(int i = 0; i < quantidade_contatos; i++){
                            if(contatosLista[i].nome.equals(nomeRemove)){
                                //System.out.println(contatosLista[i].nome);
                                removing = true;
                                for(int j = i; j < quantidade_contatos-1;j++){
                                    contatosLista[j] = contatosLista[j+1];
                                }
                                contatosLista[quantidade_contatos-1] = null;
                                quantidade_contatos--;
                                System.out.println("\n"+ nomeRemove +" removido com sucesso!");
                                break;
                            }
                        }
                        if(!removing)   System.out.println(nomeRemove +" não encontrado!");
                        break;
                        
                    case 4://ação - enviar mensagem
                        System.out.println("Nome do contato para enviar mensagens: ");
                        String nomeEnviar = ler.next();
                        ler.nextLine();
                        Boolean sending = false;
                        
                        for(int i = 0; i< quantidade_contatos; i++){
                            if(contatosLista[i].nome.equals(nomeEnviar)){
                                System.out.println("Digite uma mensagem: ");
                                String msg = ler.nextLine();
                            
                                contatosLista[i].mensagens.add(msg);               
                                System.out.println("Mensagem: "+ msg +" enviada para "+  nomeEnviar);
                                sending = true;
                                break;
                            }
                        }
                        if(!sending) System.out.println(nomeEnviar +" não encontrado!");
                        break;
                        
                    case 5:// buscar
                        System.out.println("Busque um contato pelo nome: ");
                        String nomeBuscar = ler.next();
                        Boolean finding = false;

                        for(int i = 0; i< quantidade_contatos; i++){
                            if(contatosLista[i].nome.equals(nomeBuscar)){

                                System.out.println("\nNome: "+ contatosLista[i].nome);
                                System.out.println("Telefone: "+ contatosLista[i].numero);
                                System.out.println("Mensagens: ");

                                if(!contatosLista[i].mensagens.isEmpty()){
                                    for(String mensagem : contatosLista[i].mensagens){
                                        System.out.println("\t"+ mensagem);
                                        System.out.println();
                                    }
                                    System.out.println("|*************************************|\n");
                                }else{
                                    System.out.println("\tNenhuma mensagem enviada para "+ nomeBuscar);
                                } 
                                finding = true;
                                break;
                            }
                        }
                        if(!finding) System.out.println(nomeBuscar +" não encontrado!");
                        break;

                    case 6:// alterar
                        System.out.println("Digite o nome de um contato para alterar algum dado: ");
                        String nomeAlterar = ler.next();
                        Boolean updting = false;


                        for(int i = 0; i< quantidade_contatos; i++){
                            if(contatosLista[i].nome.equals(nomeAlterar)){
                                System.out.println("O que deseja alterar em "+ nomeAlterar +"?");
                                System.out.println("(1) Nome");
                                System.out.println("(2) Numero");
                                System.out.println("(3) Nome e Numero");
                                int alterar = ler.nextInt();

                                if(alterar == 1){
                                    System.out.println("Digite o novo nome: ");
                                    String novoNome = ler.next();
                                    contatosLista[i].nome = novoNome;
                                    System.out.println("Nome alterado com sucesso em: "+  contatosLista[i].nome);

                                }else if(alterar == 2){
                                    System.out.println("Digite o novo numero: ");
                                    int novoNumero = ler.nextInt();
                                    contatosLista[i].numero = novoNumero;
                                    System.out.println("Numero alterado com sucesso em: "+  contatosLista[i].numero);

                                }else{
                                    System.out.println("Digite o novo nome: ");
                                    String novoNome = ler.next();
                                    System.out.println("Digite o novo numero: ");
                                    int novoNumero = ler.nextInt();

                                    contatosLista[i].nome = novoNome;
                                    contatosLista[i].numero = novoNumero;

                                    System.out.println("Nome alterado com sucesso em: "+  contatosLista[i].nome);
                                    System.out.println("Numero alterado com sucesso para: "+  contatosLista[i].numero);
                                }
                                updting = true;
                                break;
                            }                           
                        }

                        if(!updting) System.out.println(nomeAlterar +" não encontrado!");
                        break; 

                    case 0://encerrar
                        System.out.println("\nEncerrando o sistema!\nVolte sempre!!!");
                        euprecisodecomida = false;
                        break;

                    default://opção invalida
                        System.out.println("*** Selecione uma opção válida ***");   
                        break;
                }


            }
        }else{
            System.out.println("\nImpossivel conectar.");
            ler.close();
        }
    }
    public static void main(String[] args) {
        Brincs sistema = new Brincs();
       
        String cmd = args[0]; //caminho para o name.properties

   
        String loginDoPropCMD = (sistema.ReadProp(cmd).getProperty("login"));
        String senhaDoPropCMD = (sistema.ReadProp(cmd).getProperty("password"));
        //  String[] a = sistema.readProp(cmd).split(",");

        sistema.SistemaMensagem(sistema.Autenticar(loginDoPropCMD, senhaDoPropCMD)); // CHAMADA DO SISTEMA PRINCIPAL
        
        //sistema.SistemaMensagem(false);
        //SistemaMensagem(sistema.Autenticar(loginDoPropCMD, senhaDoPropCMD));
    }     


}
