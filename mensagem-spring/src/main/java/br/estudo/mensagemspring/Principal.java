package br.estudo.mensagemspring;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import br.estudo.mensagemspring.dao.MensagemDAO;
import br.estudo.mensagemspring.entity.Mensagem;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class Principal implements CommandLineRunner {
	
    @Autowired
	MensagemDAO baseMensagem ;


    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Principal.class);
        builder.headless(false).run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        String menu = "Escolha uma opção:\n1 - Inserir\n2 - Buscar por ID\n3 - Alterar por codigo\n4 - Exibir por codigo\n5 - Buscar por nome\n6 - Exibir todos\n7 - Exibir entre datas\n8 - Remover por id\n9 - Saair";
		char opcao = '0';
		do {
			try {
				Mensagem msg;
				int id;
				String codigo, nome;
				opcao = JOptionPane.showInputDialog(menu).charAt(0);

				switch (opcao) {
				case '1':     // Inserir
					msg = new Mensagem();
					novaMensagem(msg);
					baseMensagem.save(msg);
					break;
				case '2':     // buscar por id
					id = Integer.parseInt(JOptionPane.showInputDialog("ID PARA BUSCA"));
					msg = baseMensagem.find(id);
					listarMensagem(msg);
					break;
				case '3':     // Alterar por CODIGO
					codigo = JOptionPane.showInputDialog("CODIGO PARA ALTERAR");
					msg = baseMensagem.findByCodigo(codigo);
					if (msg != null) {
						novaMensagem(msg);
						baseMensagem.save(msg);
					} else {
						JOptionPane.showMessageDialog(null, "Não foi possível remover, pois o cliente não foi encontrado.");
					}
					break;
			case '4':     // Exibir por CPF
					codigo = JOptionPane.showInputDialog("CODIGO para busca");
					msg = baseMensagem.findByCodigo(codigo);
					listarMensagem(msg);
					break;
				case '5':     // Exibir por nome
					nome = JOptionPane.showInputDialog("NOME para busca");
					listarMensagens(baseMensagem.findByNome(nome));				
					break;
				case '6':     // Exibir todos
					listarMensagens(baseMensagem.find());
					break;
				case '7':     // Exibir todos entre datas
					   SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            Date dataInicial = dateFormat.parse(JOptionPane.showInputDialog("Data de início (dd/MM/yyyy)"));
                            Date dataFinal = dateFormat.parse(JOptionPane.showInputDialog("Data de fim (dd/MM/yyyy)"));

                            List<Mensagem> mensagensEntreDatas = baseMensagem.findByDatas(dataInicial, dataFinal);

                            if (mensagensEntreDatas.isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Nenhuma mensagem encontrada entre as datas informadas.");
                            } else {
                                listarMensagens(mensagensEntreDatas);
                            }
                        } catch (ParseException e) {
                            JOptionPane.showMessageDialog(null, "Formato de data inválido. Use o formato dd/MM/yyyy.");
						}
						break;
				case '8':     // remover por id
					// id = Integer.parseInt(JOptionPane.showInputDialog("ID PARA REMOVER"));
					codigo = JOptionPane.showInputDialog("ID PARA REMOVER");
					listarMensagem(baseMensagem.findByCodigo(codigo));
					break;
				case '9':     // Sair
					break;
				default:
					JOptionPane.showMessageDialog(null, "Opção Inválida");
					break;
				}
			} catch (NumberFormatException e) {
				log.error("Erro: {}", e.getMessage(), e);
				JOptionPane.showMessageDialog(null, "Entrada inválida: " + e.getMessage());

			} catch (Exception e) {
				log.error("Erro: {}", e.getMessage(), e);
				JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
			}
		} while(opcao != '9');	
        
    }




    public static void novaMensagem(Mensagem msg) {
		String nome = JOptionPane.showInputDialog("Nome", msg.getNome());
		String codigo = JOptionPane.showInputDialog("CODIGO", msg.getCodigo());
		String descricao = JOptionPane.showInputDialog("Descricao", msg.getDescricao());
	
		msg.setNome(nome);
		msg.setCodigo(codigo);
		msg.setDescricao(descricao);
		msg.setDataEntrada(new Date());
		// msg.setDataEntrada(new java.sql.Date(msg.getDataEntrada().getTime()));
	}

    public static void listarMensagens(List<Mensagem> mensagens) {
		StringBuilder listagem = new StringBuilder();
		for(Mensagem msg : mensagens) {
			listagem.append(msg).append("\n");
		}
		JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhuma mensagem encontrada" : listagem);
	}

	public static void listarMensagem(Mensagem msg) {
		JOptionPane.showMessageDialog(null, msg == null ? "Nenhuma mensagem encontrada" : msg);
	}



}
