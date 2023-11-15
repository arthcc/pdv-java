package ccomp.ui.unidade;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ccomp.core.exception.ValidadorException;
import ccomp.core.ui.LabeledEdit;
import ccomp.core.ui.UnidadeTabelaUI;
import ccomp.dominios.unidade.GerenciadorUnidade;
import ccomp.dominios.unidade.Unidade;
import ccomp.facade.GerenciadorSistemaFacade;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

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
