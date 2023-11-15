package ccomp.ui.pdv;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ccomp.core.ui.LabeledEdit;

import javax.swing.JLabel;
import javax.swing.JList;

public class FramePontoDeVendas extends JDialog {


	private static final long serialVersionUID = 2693925118715486463L;


	private final JPanel contentPanel = new JPanel();
	
	private FramePontoDeVendas() {

		setResizable(false);
		setTitle("PDV");
		setBounds(100, 100, 1070, 660);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setModal(true);

		LabeledEdit lblEdNomeProduto = new LabeledEdit();
		lblEdNomeProduto.setTitulo("PRODUTO:");
		lblEdNomeProduto.setBounds(10, 55, 436, 55);
		getContentPane().add(lblEdNomeProduto);
		
		JList<String> list = new JList<String>();
		list.setBounds(656, 73, 326, 417);
		getContentPane().add(list);
		
		JLabel lblNewLabel = new JLabel("itens");
		lblNewLabel.setBounds(656, 48, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("desconto");
		lblNewLabel_1.setBounds(36, 149, 76, 25);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("acrescimo");
		lblNewLabel_1_1.setBounds(128, 154, 76, 25);
		getContentPane().add(lblNewLabel_1_1);
		
		JButton btnNewButton = new JButton("remover item");
		btnNewButton.setBounds(656, 516, 121, 35);
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("VALOR VENDA");
		lblNewLabel_2.setBounds(813, 526, 99, 25);
		getContentPane().add(lblNewLabel_2);
		
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	}
	
}
