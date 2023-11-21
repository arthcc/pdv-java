package ccomp.ui.produto;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import ccomp.core.exception.ValidadorException;
import ccomp.core.ui.LabeledEdit;
import ccomp.core.ui.ProdutoTabelaUI;
import ccomp.core.ui.UnidadeComboBox;
import ccomp.dominios.produto.GerenciadorProduto;
import ccomp.dominios.produto.Produto;
import ccomp.dominios.unidade.GerenciadorUnidade;
import ccomp.dominios.unidade.Unidade;
import ccomp.facade.GerenciadorSistemaFacade;
import java.awt.Color;

public class FrameCadastroProduto extends JDialog {


	private static final long serialVersionUID = 2693925118715486463L;

	private final GerenciadorProduto gerenciadorProduto;
	private final GerenciadorUnidade gerenciadorUnidade;

	{
		gerenciadorProduto = GerenciadorSistemaFacade.getInstancia()
				.getGerenciadorProduto();
		
		gerenciadorUnidade = GerenciadorSistemaFacade.getInstancia()
				.getGerenciadorUnidade();
	}


	private final JPanel contentPanel = new JPanel();
	private ProdutoTabelaUI tabelaDeProdutos;
	private Produto produto = null;
	private LabeledEdit lblEdNomeProduto;
	private UnidadeComboBox cmbxUnidade;
	private JSpinner spinnerQtEmEstoque;
	private JSpinner spinnerPreco;
	
	protected FrameCadastroProduto(boolean emModoEdicao) {
		getContentPane().setBackground(new Color(255, 255, 255));

		setResizable(false);
		setTitle("Cadastro de produto");
		setBounds(100, 100, 472, 433);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setModal(true);

		lblEdNomeProduto = new LabeledEdit();
		lblEdNomeProduto.setOpaque(false);
		lblEdNomeProduto.setTitulo("Nome do produto:");
		lblEdNomeProduto.setBounds(10, 11, 436, 41);
		getContentPane().add(lblEdNomeProduto);

		JLabel lblUnidade = new JLabel("Unidade:");
		lblUnidade.setBounds(10, 67, 46, 14);
		getContentPane().add(lblUnidade);

		cmbxUnidade = new UnidadeComboBox();
		cmbxUnidade.carregarUnidadesDoSistema();
		cmbxUnidade.setBounds(10, 88, 131, 22);
		getContentPane().add(cmbxUnidade);

		spinnerQtEmEstoque = new JSpinner();
		spinnerQtEmEstoque.setModel(new SpinnerNumberModel(Integer.valueOf(0), 
				null, null, Integer.valueOf(1)));

		spinnerQtEmEstoque.setBounds(301, 87, 145, 23);
		getContentPane().add(spinnerQtEmEstoque);

		JLabel lblQuantidadeEmEstoque = new JLabel("Quantidade em estoque:");
		lblQuantidadeEmEstoque.setBounds(301, 66, 145, 14);
		getContentPane().add(lblQuantidadeEmEstoque);

		JLabel lblPreoDeVenda = new JLabel("Preço de venda:");
		lblPreoDeVenda.setBounds(148, 66, 145, 14);
		getContentPane().add(lblPreoDeVenda);

		spinnerPreco = new JSpinner();
		spinnerPreco.setModel(new SpinnerNumberModel(Double.valueOf(0), 
				null, null, Double.valueOf(1)));

		spinnerPreco.setBounds(148, 87, 145, 23);
		getContentPane().add(spinnerPreco);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setOpaque(false);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try 
				{
					produto = new Produto();
					produto.setNome(lblEdNomeProduto.getCampoValor().getText().toUpperCase());
					produto.setPreco(BigDecimal.valueOf((Double)spinnerPreco.getValue()));
					produto.setQuantidadeEmEstoque((Integer)spinnerQtEmEstoque.getValue());
					produto.setIdUnidade(cmbxUnidade.getUnidadeSelecionada().getId());
					produto.setDataUltimaVenda(null);
					
					if (!emModoEdicao) {
						gerenciadorProduto.registrarProduto(produto);
						JOptionPane.showMessageDialog(null, "Produto salvado!", 
								"Sucesso!", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Produto editado!", 
								"Sucesso!", JOptionPane.INFORMATION_MESSAGE);
					}
					
					dispose();
				} 
				catch (ValidadorException validadorException) 
				{
					JOptionPane.showMessageDialog(null, "Favor verifique: " +
							validadorException.getMessage(), "Erro ao gravar produto", JOptionPane.ERROR_MESSAGE);
					produto = null;
				}
			}
		});
		btnCadastrar.setBounds(301, 121, 145, 23);
		getContentPane().add(btnCadastrar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setOpaque(false);
		scrollPane.setBounds(10, 164, 436, 219);
		getContentPane().add(scrollPane);
		
		tabelaDeProdutos = new ProdutoTabelaUI();
		tabelaDeProdutos.setBackground(Color.WHITE);
		tabelaDeProdutos.setOpaque(false);
	
		tabelaDeProdutos.carregarProdutosDoSistema();
		scrollPane.setViewportView(tabelaDeProdutos);
		
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	}
	
	public void carregarValores(Produto produto) {
		lblEdNomeProduto.getCampoValor().setText(produto.getNome());
		spinnerQtEmEstoque.setValue(produto.getQuantidadeEmEstoque());
		spinnerPreco.setValue(produto.getPreco().doubleValue());
		Unidade unidade = gerenciadorUnidade.encontrarUnidadePorId(produto.getIdUnidade());
		cmbxUnidade.setSelectedItem(unidade);
		
	}
	
	public Produto getProdutoRegistrado() {
		return produto;
	}
	
	public static Produto executarCadastroProduto() {
		FrameCadastroProduto cadastroProduto = new FrameCadastroProduto(false);
		cadastroProduto.setVisible(true);
		return cadastroProduto.getProdutoRegistrado();
	}
	
}
