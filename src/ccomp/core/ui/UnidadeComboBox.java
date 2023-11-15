package ccomp.core.ui;


import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import ccomp.dominios.unidade.GerenciadorUnidade;
import ccomp.dominios.unidade.Unidade;
import ccomp.facade.GerenciadorSistemaFacade;

public class UnidadeComboBox extends JComboBox<Unidade> {

	private static final long serialVersionUID = 3027897716591886372L;

	private final GerenciadorUnidade gerenciadorUnidade;
	
	{
		gerenciadorUnidade = GerenciadorSistemaFacade.getInstancia()
				.getGerenciadorUnidade();
	}
	
	private DefaultComboBoxModel<Unidade> comboBoxModel;
	
	public UnidadeComboBox() {
		comboBoxModel = new DefaultComboBoxModel<>();
		setModel(comboBoxModel);
	}

	public void carregarUnidadesDoSistema() 
	{
		comboBoxModel.removeAllElements();
		gerenciadorUnidade.obterTodasUnidades()
			.forEach(unidade -> comboBoxModel.addElement(unidade));
		revalidate();
	}
	
	public Unidade getUnidadeSelecionada() {
		return (Unidade) getSelectedItem();
	}

}
