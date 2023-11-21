package ccomp.ui.pagamento;

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
import ccomp.core.ui.PagamentoTabelaUI;
import ccomp.dominios.formaPagamento.GerenciadorTipoPagamento;
import ccomp.dominios.formaPagamento.TipoPagamento;
import ccomp.facade.GerenciadorSistemaFacade;

public class FrameCadastroPagamento extends JDialog{


	private static final long serialVersionUID = 2693925118715486463L;

	private final GerenciadorTipoPagamento gerenciadorPagamento;

	private TipoPagamento pagamento;

	{
		gerenciadorPagamento = GerenciadorSistemaFacade.getInstancia
				().getGerenciadorTipoPagamento();
	}

	private final JPanel contentPanel = new JPanel();
	private PagamentoTabelaUI tabelaDePagamento;

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

		tabelaDePagamento = new PagamentoTabelaUI();
		tabelaDePagamento.carregarPagamentosDoSistema();
		scrollPane.setViewportView(tabelaDePagamento);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					pagamento = new TipoPagamento("");
					pagamento.setNome(lblEdNomePagamento.getCampoValor().getText());

					gerenciadorPagamento.registrarPagamento(pagamento);

					JOptionPane.showMessageDialog(null, "Forma de Pagamento salva!", 
							"Sucesso!", JOptionPane.INFORMATION_MESSAGE);

					tabelaDePagamento.carregarPagamentosDoSistema();

				} 
				catch (ValidadorException validadorException) 
				{
					JOptionPane.showMessageDialog(null, "Favor verifique: " +
							validadorException.getMessage(), "Erro ao gravar", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCadastrar.setBounds(314, 63, 132, 23);
		getContentPane().add(btnCadastrar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long idPagamento = tabelaDePagamento.getIdPagamentoSelecionado();
				if (idPagamento == -1L) {
					JOptionPane.showMessageDialog(null, "Selecione um tipo de pagamento antes.", 
							"Erro ao excluir", JOptionPane.WARNING_MESSAGE);
					return;
				}
				gerenciadorPagamento.deletarPagamentoPorId(idPagamento);
				JOptionPane.showMessageDialog(null, "Tipo de pagamento excluido.", 
						"Sucesso", JOptionPane.INFORMATION_MESSAGE);

				tabelaDePagamento.carregarPagamentosDoSistema();
			}
		});
		btnExcluir.setBounds(10, 63, 79, 23);
		getContentPane().add(btnExcluir);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long idPagamento = tabelaDePagamento.getIdPagamentoSelecionado();
				if (idPagamento == -1L) {
					JOptionPane.showMessageDialog(null, "Selecione um tipo de pagamento antes.", 
							"Erro ao alterar", JOptionPane.WARNING_MESSAGE);
					return;
				}
				try {
					
					TipoPagamento tpPagamento = gerenciadorPagamento.encontrarPagamentoPorId(idPagamento);

					String novoNomeFormaDePagamento = JOptionPane.showInputDialog(null, "<html><center>Só é possível alterar o "
							+ "nome do tipo de pagamento<br>Informe o <b>novo</b> nome:</center></html>", tpPagamento.getNome());

					if (gerenciadorPagamento.encontrarPagamentoPorNome(novoNomeFormaDePagamento) != null)
						throw new ValidadorException("Esse nome já foi usado anteriormente.");

					tpPagamento.setNome(novoNomeFormaDePagamento);
					
					JOptionPane.showMessageDialog(null, "Tipo de pagamento alterado.", 
							"Sucesso", JOptionPane.INFORMATION_MESSAGE);
				}
				catch (ValidadorException validadorException) 
				{
					JOptionPane.showMessageDialog(null, "Favor verifique: " +
							validadorException.getMessage(), "Erro ao gravar", JOptionPane.ERROR_MESSAGE);
				}

				tabelaDePagamento.carregarPagamentosDoSistema();
			}
		});
		btnAlterar.setBounds(99, 63, 79, 23);
		getContentPane().add(btnAlterar);

		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	}

	public TipoPagamento getPagamentoRegistrado() {
		return pagamento;
	}

	public static TipoPagamento executarCadastroPagamento() {
		FrameCadastroPagamento cadastroPagamento = new FrameCadastroPagamento();
		cadastroPagamento.setVisible(true);
		return cadastroPagamento.getPagamentoRegistrado();
	}
}
