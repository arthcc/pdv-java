package ccomp.ui.produto;

import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ccomp.core.Utilitarios;
import ccomp.core.ui.LabeledEdit;
import ccomp.core.ui.ProdutoTabelaUI;
import ccomp.dominios.produto.GerenciadorProduto;
import ccomp.dominios.produto.Produto;
import ccomp.facade.GerenciadorSistemaFacade;

import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameLocalizadorProduto extends JDialog {


	private static final long serialVersionUID = 2693925118715486463L;

	private final JPanel contentPanel = new JPanel();
	private ProdutoTabelaUI tabelaDeProdutos;

	private final GerenciadorProduto gerenciadorProduto;

	{
		gerenciadorProduto = GerenciadorSistemaFacade.getInstancia()
				.getGerenciadorProduto();
	}

	private Produto produtoSelecionado;

	private FrameLocalizadorProduto(boolean modoLocalizacaoApenas) {
		setTitle("Localização de produto");
		setBounds(100, 100, 553, 628);
		setLocationRelativeTo(null);
		setModal(true);

		LabeledEdit lblEdNomeProduto = new LabeledEdit();

		lblEdNomeProduto.getCampoValor().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				String trecho = lblEdNomeProduto.getCampoValor().getText();
				if (Utilitarios.isNullOrEmpty(trecho)) {
					tabelaDeProdutos.limparTabela();
					return;
				}
				tabelaDeProdutos.carregarProdutosDoSistema(trecho);

			}
		});
		lblEdNomeProduto.setTitulo("Trecho do nome do produto:");

		JScrollPane scrollPane = new JScrollPane();

		tabelaDeProdutos = new ProdutoTabelaUI();

		scrollPane.setViewportView(tabelaDeProdutos);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(!modoLocalizacaoApenas);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Long idProduto = tabelaDeProdutos.getIdProdutoSelecionado();

				gerenciadorProduto.getRepositorio().encontrarPorId(idProduto)
				.ifPresent(produto -> gerenciadorProduto
						.deletarProduto(produto));

				lblEdNomeProduto.getCampoValor().setText("");
				tabelaDeProdutos.carregarProdutosDoSistema();

			}
		});

		JButton btnEditar = new JButton("Editar");
		btnEditar.setEnabled(!modoLocalizacaoApenas);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				Long idProduto = tabelaDeProdutos.getIdProdutoSelecionado();

				gerenciadorProduto.encontrarProdutoPorId(idProduto)
				.ifPresent(produtoASerEditado -> 
				{
					/*Cadastro de produto em modo de edição*/
					FrameCadastroProduto frameCadastroProduto = new FrameCadastroProduto(true); 
					frameCadastroProduto.carregarValores(produtoASerEditado);
					frameCadastroProduto.setVisible(true);

					Produto produtoArbitrarioEditado = frameCadastroProduto.getProdutoRegistrado();

					/* substituindo os valores editados do produto */

					produtoASerEditado.setNome(produtoArbitrarioEditado.getNome());
					produtoASerEditado.setPreco(produtoArbitrarioEditado.getPreco());
					produtoASerEditado.setQuantidadeEmEstoque(produtoArbitrarioEditado.getQuantidadeEmEstoque());
					produtoASerEditado.setIdUnidade(produtoArbitrarioEditado.getIdUnidade());

					JOptionPane.showMessageDialog(FrameLocalizadorProduto.this, "O produto \"" + 
							produtoASerEditado.getNome() + "\" foi editado com sucesso." );
				});

			}
		});

		JButton btnSelecionar = new JButton("Selecionar");
		btnSelecionar.setEnabled(modoLocalizacaoApenas);
		btnSelecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				produtoSelecionado = gerenciadorProduto.encontrarProdutoPorId(
						tabelaDeProdutos.getIdProdutoSelecionado())
						.orElse(null);
				dispose();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(10)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
								.addComponent(lblEdNomeProduto, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE))
						.addGap(10))
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(btnSelecionar, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 293, Short.MAX_VALUE)
						.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnExcluir)
						.addContainerGap())
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(10)
						.addComponent(lblEdNomeProduto, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addGap(12)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 486, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnExcluir)
								.addComponent(btnEditar)
								.addComponent(btnSelecionar))
						.addContainerGap(13, Short.MAX_VALUE))
				);
		getContentPane().setLayout(groupLayout);

		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));


		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("Fechando");
				tabelaDeProdutos.limparTabela();
			}

		});

	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}
	
	public static void executarLocalizadorDeProduto() {
		new FrameLocalizadorProduto(false)
			.setVisible(true);
	}
	
	public static Produto localizarProduto() {
		FrameLocalizadorProduto localizadorProduto = new FrameLocalizadorProduto(true);
		localizadorProduto.setVisible(true);
		return localizadorProduto.getProdutoSelecionado();
	}
}
