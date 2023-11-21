package ccomp.ui.unidade;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import ccomp.core.exception.ValidadorException;
import ccomp.core.ui.LabeledEdit;
import ccomp.core.ui.UnidadeTabelaUI;
import ccomp.dominios.unidade.GerenciadorUnidade;
import ccomp.dominios.unidade.Unidade;
import ccomp.facade.GerenciadorSistemaFacade;

public class FrameCadastroUnidade extends JDialog {


	private static final long serialVersionUID = 2693925118715486463L;

	private final GerenciadorUnidade gerenciadorUnidade;

	{
		gerenciadorUnidade = GerenciadorSistemaFacade.getInstancia
				().getGerenciadorUnidade();
	}

	private final JPanel contentPanel = new JPanel();
	private Unidade unidade = null;
	private UnidadeTabelaUI tabelaDeUnidades;
	
	private FrameCadastroUnidade() {

		setResizable(false);
		setTitle("Cadastro de unidade");
		setBounds(100, 100, 472, 388);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setModal(true);

		LabeledEdit lblEdNomeUnidade = new LabeledEdit();
		lblEdNomeUnidade.setTitulo("Nome da unidade:");
		lblEdNomeUnidade.setBounds(10, 11, 436, 41);
		getContentPane().add(lblEdNomeUnidade);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 97, 436, 241);
		getContentPane().add(scrollPane);
		
		tabelaDeUnidades = new UnidadeTabelaUI();
		tabelaDeUnidades.carregarUnidadesDoSistema();
		scrollPane.setViewportView(tabelaDeUnidades);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try 
				{
					unidade = new Unidade();
					unidade.setNome(lblEdNomeUnidade.getCampoValor().getText());
					
					gerenciadorUnidade.registrarUnidade(unidade);

					JOptionPane.showMessageDialog(null, "Unidade salvada!", 
							"Sucesso!", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				} 
				catch (ValidadorException validadorException) 
				{
					JOptionPane.showMessageDialog(null, "Favor verifique: " +
							validadorException.getMessage(), "Erro ao gravar unidade", JOptionPane.ERROR_MESSAGE);
					unidade = null;
				}
				
			}
		});
		btnCadastrar.setBounds(314, 63, 132, 23);
		getContentPane().add(btnCadastrar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long idUnidade = tabelaDeUnidades.getIdUnidadeSelecionado();
				if (idUnidade == -1L) {
					JOptionPane.showMessageDialog(null, "Selecione uma unidade antes.", 
							"Erro ao excluir", JOptionPane.WARNING_MESSAGE);
					return;
				}
				gerenciadorUnidade.deletarUnidadePorId(idUnidade);
				JOptionPane.showMessageDialog(null, "Unidade excluida.", 
						"Sucesso", JOptionPane.INFORMATION_MESSAGE);

				tabelaDeUnidades.carregarUnidadesDoSistema();
			}
		});
		btnExcluir.setBounds(10, 63, 63, 23);
		getContentPane().add(btnExcluir);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long idUnidade = tabelaDeUnidades.getIdUnidadeSelecionado();
				if (idUnidade == -1L) {
					JOptionPane.showMessageDialog(null, "Selecione uma unidade antes.", 
							"Erro ao alterar", JOptionPane.WARNING_MESSAGE);
					return;
				}
				try {
					
					Unidade unidade = gerenciadorUnidade.encontrarUnidadePorId(idUnidade);

					String novoNomeUnidade = JOptionPane.showInputDialog(null, "<html><center>Só é possível alterar o "
							+ "nome da unidade<br>Informe o <b>novo</b> nome:</center></html>", unidade.getNome());

					if (gerenciadorUnidade.encontrarUnidadePorNome(novoNomeUnidade) != null)
						throw new ValidadorException("Esse nome já foi usado anteriormente.");

					unidade.setNome(novoNomeUnidade);
					
					JOptionPane.showMessageDialog(null, "Unidade alterada.", 
							"Sucesso", JOptionPane.INFORMATION_MESSAGE);
				}
				catch (ValidadorException validadorException) 
				{
					JOptionPane.showMessageDialog(null, "Favor verifique: " +
							validadorException.getMessage(), "Erro ao gravar", JOptionPane.ERROR_MESSAGE);
				}

				tabelaDeUnidades.carregarUnidadesDoSistema();
			}
		});
		btnAlterar.setBounds(83, 63, 119, 23);
		getContentPane().add(btnAlterar);
		
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	}
	
	public Unidade getUnidadeRegistrado() {
		return unidade;
	}
	
	public static Unidade executarCadastroUnidade() {
		FrameCadastroUnidade cadastroUnidade = new FrameCadastroUnidade();
		cadastroUnidade.setVisible(true);
		return cadastroUnidade.getUnidadeRegistrado();
	}
}
