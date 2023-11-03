package br.estudo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import br.estudo.dao.MensagemDAO;
import br.estudo.dao.MensagemJDBCDAO;
import br.estudo.dao.MensagemJPADAO;
import br.estudo.dao.MensagemSpringJdbcDAO;
import br.estudo.entity.Mensagem;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Principal {
    public static void main(String[] args) {
        MensagemDAO baseMsg = new MensagemSpringJdbcDAO();
    
        StringBuilder menu = new StringBuilder("Deseja quala opção:\n")
            .append("[1] - Nova mensagem\n")
            .append("[2] - Atualizar mensagem por codigo\n")
            .append("[3] - Remover mensagem por codigo\n")
            .append("[4] - Remover mensagem por id\n")
            .append("[5] - Exibir mensagem por id\n")
            .append("[6] - Exibir mensagem por codigo\n")
            .append("[7] - Exibir mensagem por descrição\n")
            .append("[8] -  Exibir mensagens entre datas\n") 
            .append("[9] - Sair\n");
    
        char opcao = '0';
        do {

            try {
                Mensagem msg;
                String codigo, descricao;
                int id;
                opcao = JOptionPane.showInputDialog(menu).charAt(0);
                
                switch (opcao) {
                    case '1':
                        msg = new Mensagem();
                        novaMensagem(msg);
                        baseMsg.save(msg);
                        break;
                    case '2':
                        codigo = JOptionPane.showInputDialog("Digite o CODIGO da mensagem que deseja editar");
                        msg = baseMsg.findByCodigo(codigo);
                        novaMensagem(msg);
                        baseMsg.save(msg);
                        break;
                    case '3':
                        codigo = JOptionPane.showInputDialog("CODIGO para remover");
                        msg = baseMsg.findByCodigo(codigo);
                        if (msg != null) {
                            baseMsg.remove(msg.getId());
                            JOptionPane.showMessageDialog(null, "Mensagem removida com sucesso pelo CODIGO.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Não foi possível remover, pois a mensagem não foi encontrado por CODIGO.");
                        }
                        break;
                    case '4':
                        String idStr = JOptionPane.showInputDialog("ID para remover");
                       
                        try {
                            id = Integer.parseInt(idStr);
                        } catch (NumberFormatException e) {
                            // Trate o erro caso a entrada não seja um número válido
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(null, "ID inválido. Insira um número válido.");
                            return;
                        }

                        msg = baseMsg.find(id);
                        if (msg != null) {
                            baseMsg.remove(msg.getId());
                            JOptionPane.showMessageDialog(null, "Mensagem removida com sucesso pelo ID.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Não foi possível remover, pois a mensagem não foi encontrado por ID.");
                        }
                        break;
                    case '5':
                        id = Integer.parseInt(JOptionPane.showInputDialog("ID PARA BUSCA"));
                        msg = baseMsg.find(id);
                        listarMensagem(msg);
                        break;
                    case '6':
                        /*   
                        codigo = JOptionPane.showInputDialog("CODIGO PARA BUSCA");
                        msg = baseMsg.findByCodigo(codigo);
                        listarMensagem(msg); */
                        //ver todos
                        listarMensagens(baseMsg.find());
                        break;
                    case '7':
                        descricao = JOptionPane.showInputDialog("DESCRIÇÃO PARA BUSCA");
                        listarMensagens(baseMsg.findByDescricao(descricao));
                        // descricao = JOptionPane.showInputDialog("NOME PARA BUSCA");
                        // listarMensagens(baseMsg.findByNome(descricao));
                        break;
                    case '8':
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            Date dataInicial = dateFormat.parse(JOptionPane.showInputDialog("Data de início (dd/MM/yyyy)"));
                            Date dataFinal = dateFormat.parse(JOptionPane.showInputDialog("Data de fim (dd/MM/yyyy)"));

                            List<Mensagem> mensagensEntreDatas = baseMsg.findByDataEntrada(dataInicial, dataFinal);

                            if (mensagensEntreDatas.isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Nenhuma mensagem encontrada entre as datas informadas.");
                            } else {
                                listarMensagens(mensagensEntreDatas);
                            }
                        } catch (ParseException e) {
                            JOptionPane.showMessageDialog(null, "Formato de data inválido. Use o formato dd/MM/yyyy.");
                        }
                        break;
                    case '9':
                        log.info("*******FINALIZADO*******");
                        break;
                    
                
                    default:
                        JOptionPane.showMessageDialog(null, "Opção Inválida");

                        break;
                }
            } catch (Exception e) {
				log.error(e.getMessage(), e);
				JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
			}
        } while (opcao != '9');

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
