package ccomp.core.ui;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ccomp.core.Utilitarios;
import ccomp.dominios.produto.GerenciadorProduto;
import ccomp.dominios.produto.Produto;
import ccomp.dominios.unidade.GerenciadorUnidade;
import ccomp.facade.GerenciadorSistemaFacade;

public class ProdutoTabelaUI extends JTable {


	private final GerenciadorProduto gerenciadorProduto;
	private final GerenciadorUnidade gerenciadorUnidade;

	{
		gerenciadorProduto = GerenciadorSistemaFacade.getInstancia()
				.getGerenciadorProduto();
		
		gerenciadorUnidade = GerenciadorSistemaFacade.getInstancia()
				.getGerenciadorUnidade();
	}

	private static final long serialVersionUID = 2101965585496665196L;

	private DefaultTableModel tableModel;
	
	public ProdutoTabelaUI() {
		super();
		
		tableModel = new CustomTableModel();
		
		tableModel.addColumn("ID");
		tableModel.addColumn("NOME");
		tableModel.addColumn("UNIDADE");
		tableModel.addColumn("PREÇO");
		tableModel.addColumn("ESTOQUE ATUAL");
		tableModel.addColumn("ULTIMA VENDA");

		setRowHeight(30);
		setShowHorizontalLines(false);
		setShowVerticalLines(false);
		setModel(tableModel);

	}
	
	public void carregarProdutosDoSistema(String filtroDeNome) {
		limparTabela();
		gerenciadorProduto.obterTodosProdutos()
			.stream()
			.filter(p -> filtroDeNome == null || (filtroDeNome != null && p.getNome()
				.contains(filtroDeNome.toUpperCase())) || filtroDeNome.equals("%"))
			.forEach(produto -> 
			{
				tableModel.addRow(criarDadosProduto(produto));
			});
		revalidate();
	}
	
	
	private Object[] criarDadosProduto(Produto produto) {
		
		Object[] dados = new Object[6];
		
		dados[0] =  produto.getId();
		dados[1] = produto.getNome();
		
		dados[2] = gerenciadorUnidade.encontrarUnidadePorId(
				produto.getIdUnidade()).getNome();
		
		dados[3] = produto.getPreco();
		dados[4] = produto.getQuantidadeEmEstoque();
		
		dados[5] = produto.getDataUltimaVenda() == null ? "-" :
			 Utilitarios.dateToString(produto.getDataUltimaVenda());
		
		return dados;
		
	}
	
	
	public Long getIdProdutoSelecionado() {
		int linhaSelecionadaIndex = getSelectedRow();
		Long idProduto = (Long)
				((Vector<?>)((DefaultTableModel)tableModel)
					.getDataVector()
					.get(linhaSelecionadaIndex))
				.get(0);
		return idProduto;
	}
	
	public void limparTabela() {
		tableModel.getDataVector().clear();
	}
	
	public void carregarProdutosDoSistema() {
		carregarProdutosDoSistema(null);
	}
	
}
