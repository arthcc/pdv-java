package ccomp.ui.pagamento;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import ccomp.core.exception.ValidadorException;
import ccomp.core.ui.LabeledEdit;
import ccomp.core.ui.UnidadePagamentoUI;
import ccomp.core.ui.UnidadeTabelaUI;
import ccomp.dominios.formaPagamento.GerenciadorTipoPagamento;
import ccomp.dominios.formaPagamento.TipoPagamento;
import ccomp.dominios.pagamento.Pagamento;
import ccomp.dominios.unidade.GerenciadorUnidade;
import ccomp.dominios.unidade.Unidade;
import ccomp.facade.GerenciadorSistemaFacade;
import ccomp.ui.unidade.FrameCadastroUnidade;

public class FrameCadastroPagamento extends JDialog{
	
	
	private static final long serialVersionUID = 2693925118715486463L;

	private final GerenciadorTipoPagamento gerenciadorPagamento;
	
	private Pagamento pagamento;

	{
		gerenciadorPagamento = GerenciadorSistemaFacade.getInstancia
				().getGerenciadorTipoPagamento();
	}

	private final JPanel contentPanel = new JPanel();
	private UnidadePagamentoUI tabelaDePagamento;
	
	public FrameCadastroPagamento() {

		setResizable(false);
		setTitle("Cadastro de Forma de Pagamento");
		setBounds(100, 100, 472, 388);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setModal(true);

		LabeledEdit lblEdNomePagamento = new LabeledEdit();
		lblEdNomePagamento.setTitulo("Forma de Pagamento:");
		lblEdNomePagamento.setBounds(10, 11, 436, 41);
		getContentPane().add(lblEdNomePagamento);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 97, 436, 241);
		getContentPane().add(scrollPane);
		
		tabelaDePagamento = new UnidadePagamentoUI();
		tabelaDePagamento.carregarPagamentoDoSistema();
		scrollPane.setViewportView(tabelaDePagamento);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try 
				{
					pagamento = new Pagamento();
					pagamento.setNome(lblEdNomePagamento.getCampoValor().getText());
					
					gerenciadorPagamento.registrarPagamento(pagamento);

					JOptionPane.showMessageDialog(null, "Forma de Pagamento salva!", 
							"Sucesso!", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				} 
				catch (ValidadorException validadorException) 
				{
					JOptionPane.showMessageDialog(null, "Favor verifique: " +
							validadorException.getMessage(), "Erro ao gravar", JOptionPane.ERROR_MESSAGE);
					pagamento = null;
				}
				
			}
		});
		btnCadastrar.setBounds(314, 63, 132, 23);
		getContentPane().add(btnCadastrar);
		
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	}
	
	public Pagamento getPagamentoRegistrado() {
		return pagamento;}
		
	public static Pagamento executarCadastroPagamento() {
	FrameCadastroPagamento cadastroPagamento = new FrameCadastroPagamento();
	cadastroPagamento.setVisible(true);
	return cadastroPagamento.getPagamentoRegistrado();
		}
}
