package ccomp.core.ui;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ccomp.dominios.formaPagamento.GerenciadorTipoPagamento;
import ccomp.dominios.pagamento.Pagamento;
import ccomp.dominios.unidade.GerenciadorUnidade;
import ccomp.dominios.unidade.Unidade;
import ccomp.facade.GerenciadorSistemaFacade;

public class UnidadePagamentoUI extends JTable {


	private final GerenciadorTipoPagamento gerenciadorPagamento;

	{
		gerenciadorPagamento = GerenciadorSistemaFacade.getInstancia()
				.getGerenciadorTipoPagamento();
	}

	private static final long serialVersionUID = 2101965585496665196L;

	private DefaultTableModel tableModel;
	
	public UnidadePagamentoUI() {
		super();
		tableModel = new CustomTableModel();
		tableModel.addColumn("ID");
		tableModel.addColumn("NOME");

		setRowHeight(30);
		setShowHorizontalLines(false);
		setShowVerticalLines(false);
		setModel(tableModel);
	
	}
	
	public void carregarPagamentoDoSistema(String filtroDeNome) {
		limparTabela();
		gerenciadorPagamento.obterTodosPagamentos()
			.stream()
			.filter(p -> filtroDeNome == null || (filtroDeNome != null && p.getNome().contains(filtroDeNome.toUpperCase())))
			.forEach(produto -> 
			{
				tableModel.addRow(criarDadosPagamento(produto));
			});
		revalidate();
	}
	
	
	private Object[] criarDadosPagamento(Pagamento pagamento) {
		
		Object[] dados = new Object[7];
		dados[0] =  String.format("%06d", pagamento.getId());
		dados[1] = pagamento.getNome();
		
		return dados;
		
	}
	
	public void limparTabela() {
		tableModel.getDataVector().clear();
	}
	
	public void carregarPagamentoDoSistema() {
		carregarPagamentoDoSistema(null);
	}
	
}
