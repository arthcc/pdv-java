package ccomp.core.ui;

import javax.swing.table.DefaultTableModel;

public class CustomTableModel extends DefaultTableModel {

	private static final long serialVersionUID = -4983383300388186864L;

	@Override
	public boolean isCellEditable(int row, int column) {
		/* não permitir edição da celula */
		return false; 
	}
	
}
