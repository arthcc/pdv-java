package ccomp.core.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class LabeledEdit extends JPanel {

	
	private static final long serialVersionUID = 509390001096404242L;
	private JLabel lblTitulo;
	private JTextField tfValor;

	public LabeledEdit() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		lblTitulo = new JLabel("New label");
		springLayout.putConstraint(SpringLayout.NORTH, lblTitulo, 0, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblTitulo, 0, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, lblTitulo, 0, SpringLayout.EAST, this);
		add(lblTitulo);
		
		tfValor = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, tfValor, 6, SpringLayout.SOUTH, lblTitulo);
		springLayout.putConstraint(SpringLayout.WEST, tfValor, 0, SpringLayout.WEST, lblTitulo);
		springLayout.putConstraint(SpringLayout.EAST, tfValor, 0, SpringLayout.EAST, lblTitulo);
		add(tfValor);
		tfValor.setColumns(10);
	}

	public void setTitulo(String titulo) {
		lblTitulo.setText(titulo);
	}
	
	public JTextField getCampoValor() {
		return tfValor;
	}
	
}
