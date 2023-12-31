package ccomp.core.ui;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import ccomp.dominios.unidade.GerenciadorUnidade;
import ccomp.dominios.unidade.Unidade;
import ccomp.facade.GerenciadorSistemaFacade;

public class UnidadeTabelaUI extends JTable {


	private final GerenciadorUnidade gerenciadorUnidade;

	{
		gerenciadorUnidade = GerenciadorSistemaFacade.getInstancia()
				.getGerenciadorUnidade();
	}

	private static final long serialVersionUID = 2101965585496665196L;

	private DefaultTableModel tableModel;
	
	public UnidadeTabelaUI() {
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
	
	public void carregarUnidadesDoSistema(String filtroDeNome) {
		limparTabela();
		gerenciadorUnidade.obterTodasUnidades()
			.stream()
			.filter(p -> filtroDeNome == null || (filtroDeNome != null && p.getNome().contains(filtroDeNome.toUpperCase())))
			.forEach(produto -> 
			{
				tableModel.addRow(criarDadosUnidade(produto));
			});
		clearSelection();
		revalidate();
	}
	
	
	private Object[] criarDadosUnidade(Unidade unidade) {
		
		Object[] dados = new Object[7];
		dados[0] =  unidade.getId();
		dados[1] = unidade.getNome();
		
		return dados;
		
	}
	
	public Long getIdUnidadeSelecionado() {
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
	
	public void carregarUnidadesDoSistema() {
		carregarUnidadesDoSistema(null);
	}
	
}
