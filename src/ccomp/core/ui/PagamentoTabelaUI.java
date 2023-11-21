package ccomp.core.ui;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import ccomp.dominios.formaPagamento.GerenciadorTipoPagamento;
import ccomp.dominios.formaPagamento.TipoPagamento;
import ccomp.facade.GerenciadorSistemaFacade;

public class PagamentoTabelaUI extends JTable {


	private final GerenciadorTipoPagamento gerenciadorTipoPagamento;

	{
		gerenciadorTipoPagamento = GerenciadorSistemaFacade.getInstancia()
				.getGerenciadorTipoPagamento();
	}

	private static final long serialVersionUID = 2101965585496665196L;

	private DefaultTableModel tableModel;
	
	public PagamentoTabelaUI() {
		super();
		tableModel = new CustomTableModel();
		tableModel.addColumn("ID");
		tableModel.addColumn("NOME");

		setRowHeight(30);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setShowHorizontalLines(false);
		setShowVerticalLines(false);
		setModel(tableModel);
	
	}
	
	public void carregarPagamentosDoSistema(String filtroDeNome) {
		limparTabela();
		gerenciadorTipoPagamento.obterTodosPagamentos()
			.stream()
			.filter(p -> filtroDeNome == null || (filtroDeNome != null && p.getNome().contains(filtroDeNome.toUpperCase())))
			.forEach(pagamento -> 
			{
				tableModel.addRow(criarDadosPagamento(pagamento));
			});
		clearSelection();
		revalidate();
	}
	
	public Long getIdPagamentoSelecionado() {
		int linhaSelecionadaIndex = getSelectedRow();
		if (linhaSelecionadaIndex == -1) {
			return -1L;
		}
		Long idPagamento = (Long)
				((Vector<?>)((DefaultTableModel)tableModel)
					.getDataVector()
					.get(linhaSelecionadaIndex))
				.get(0);
		return idPagamento;
	}
	
	private Object[] criarDadosPagamento(TipoPagamento pagamento) {
		Object[] dados = new Object[7];
		dados[0] = pagamento.getId();
		dados[1] = pagamento.getNome();
		return dados;
	}
	
	public void limparTabela() {
		tableModel.getDataVector().clear();
	}
	
	public void carregarPagamentosDoSistema() {
		carregarPagamentosDoSistema(null);
	}
	
}
