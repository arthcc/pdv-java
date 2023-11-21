package ccomp.core.ui;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import ccomp.core.Utilitarios;
import ccomp.dominios.venda.GerenciadorVenda;
import ccomp.dominios.venda.Venda;
import ccomp.facade.GerenciadorSistemaFacade;

public class VendaTabelaUI extends JTable {


	private final GerenciadorVenda gerenciadorVenda;

	{
		gerenciadorVenda = GerenciadorSistemaFacade.getInstancia()
				.getGerenciadorVenda();
	}

	private static final long serialVersionUID = 2101965585496665196L;

	private DefaultTableModel tableModel;
	
	public VendaTabelaUI() {
		super();
		tableModel = new CustomTableModel();
		tableModel.addColumn("ID DA VENDA");
		tableModel.addColumn("DATA VENDA");
		tableModel.addColumn("TOTAL DE ITENS");
		tableModel.addColumn("VALOR TOTAL");
		
		setRowHeight(30);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setShowHorizontalLines(false);
		setShowVerticalLines(false);
		setModel(tableModel);
	
	}
	
	public void carregarVendasDoSistema(String filtroDeNome) {
		limparTabela();
		gerenciadorVenda.obterTodasAsVendas()
			.stream()
			.filter(venda -> filtroDeNome == null || (filtroDeNome != null && Utilitarios.dateToString(venda
					.getDataTempoVenda()).contains(filtroDeNome)))
			.forEach(venda -> 
			{
				tableModel.addRow(criarDadosVenda(venda));
			});
		clearSelection();
		revalidate();
	}
	
	
	private Object[] criarDadosVenda(Venda venda) {
		Object[] dados = new Object[7];
		dados[0] = venda.getId();
		dados[1] = Utilitarios.dateToString(venda.getDataTempoVenda());
		dados[2] = venda.getItens().size();
		dados[3] = String.format("R$ %,2f", venda.getValorTotal().doubleValue());
		return dados;
	}
	
	public Long getIdVendaSelecionado() {
		int linhaSelecionadaIndex = getSelectedRow();
		if (linhaSelecionadaIndex == -1) {
			return -1L;
		}
		Long idUnidade = (Long)
				((Vector<?>)((DefaultTableModel)tableModel)
					.getDataVector()
					.get(linhaSelecionadaIndex))
				.get(0);
		return idUnidade;
	}
	
	public void limparTabela() {
		tableModel.getDataVector().clear();
	}
	
	public void carregarVendasDoSistema() {
		carregarVendasDoSistema(null);
	}
	
}
