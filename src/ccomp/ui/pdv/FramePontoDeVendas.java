package ccomp.ui.pdv;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import ccomp.core.Icones;
import ccomp.core.Utilitarios;
import ccomp.core.ui.LabeledEdit;
import ccomp.dominios.venda.ItemVenda;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import javax.swing.border.LineBorder;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JFormattedTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;

public class FramePontoDeVendas extends JFrame implements ActionListener {


	private static final long serialVersionUID = 2693925118715486463L;
	
	public static final String CMD_FECHAR = "fechar";
	public static final String CMD_FULLSCREEN = "fullscreen";

	
	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		FramePontoDeVendas pontoDeVendas = new FramePontoDeVendas();
		pontoDeVendas.setVisible(true);

	}

	private final JPanel contentPanel;
	private JTextField textField;

	private FramePontoDeVendas() {

		/* Configuração propriedades do JFrame */

		setTitle("PDV");
		setUndecorated(true);
		setResizable(false);
		setBounds(100, 100, 1070, 660);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		/* Configuração propriedades do Panel de fundo */

		contentPanel = new CustomGradientPanel(Color.decode("#a855f7"), 
				Color.decode("#818cf8"));
		setContentPane(contentPanel);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		JButton btnFechar = new JButton();
		btnFechar.setIcon(icone24x24("/ccomp/icons/pdv/fechar.png"));
		btnFechar.setActionCommand(CMD_FECHAR);
		btnFechar.setContentAreaFilled(false);
		btnFechar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFechar.setFocusPainted(false);
		btnFechar.addActionListener(this);
		btnFechar.setBorder(null);
		btnFechar.setBackground(null);
		btnFechar.setOpaque(false);

		JButton btnFullScreen = new JButton();
		btnFullScreen.setIcon(icone24x24("/ccomp/icons/pdv/fullscreen.png"));
		btnFullScreen.setActionCommand(CMD_FULLSCREEN);
		btnFullScreen.setContentAreaFilled(false);
		btnFullScreen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFullScreen.setFocusPainted(false);
		btnFullScreen.addActionListener(this);
		btnFullScreen.setBorder(null);
		btnFullScreen.setBackground(null);
		btnFullScreen.setOpaque(false);

		JPanel pnProduto = new JPanel();
		pnProduto.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 255), 2, true), 
				"Produto", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 255, 255)));
		pnProduto.setOpaque(false);
		
		pnProduto.setLayout(new BoxLayout(pnProduto, BoxLayout.X_AXIS));

		Component horizontalStrut = Box.createHorizontalStrut(10);
		pnProduto.add(horizontalStrut);
		
		textField = new JTextField();
		textField.setFont(new Font("Verdana", Font.BOLD, 30));
		textField.setOpaque(false);
		textField.setCaretColor(Color.WHITE);
		textField.setBorder(null);
		textField.setForeground(Color.WHITE);
		textField.setText("000001");
		pnProduto.add(textField);
		textField.setColumns(10);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		pnProduto.add(horizontalStrut_1);
		
		
		JPanel pnDescontoAcrescimo = new JPanel();
		pnDescontoAcrescimo.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 255), 2, true), 
				"Desconto / Acrescimo", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 255, 255)));
		pnDescontoAcrescimo.setOpaque(false);
		
		pnDescontoAcrescimo.setLayout(new BoxLayout(pnDescontoAcrescimo, BoxLayout.X_AXIS));
		
		JFormattedTextField txDesconto = new JFormattedTextField();
		txDesconto.setForeground(new Color(255, 102, 102));
		txDesconto.setCaretColor(txDesconto.getForeground());
		txDesconto.setFont(new Font("Verdana", Font.BOLD, 28));
		txDesconto.setHorizontalAlignment(SwingConstants.CENTER);
		txDesconto.setValue(new Double(0D));
		txDesconto.setOpaque(false);
		txDesconto.setBorder(new EmptyBorder(2, 2, 2, 2));
		
	    Component horizontalStrut_3 = Box.createHorizontalStrut(2);
	    pnDescontoAcrescimo.add(horizontalStrut_3);
	    txDesconto.setFormatterFactory(
	    		new DefaultFormatterFactory(criarFormatacaoDecimal("-")));       
		pnDescontoAcrescimo.add(txDesconto);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(2);
		pnDescontoAcrescimo.add(horizontalStrut_2);
		
		JFormattedTextField txAcrescimo = new JFormattedTextField();
		txAcrescimo.setForeground(new Color(102, 204, 0));
		txAcrescimo.setCaretColor(txAcrescimo.getForeground());
		txAcrescimo.setFont(new Font("Verdana", Font.BOLD, 28));
		txAcrescimo.setHorizontalAlignment(SwingConstants.CENTER);
		txAcrescimo.setValue(new Double(0D));
		txAcrescimo.setOpaque(false);
		txAcrescimo.setBorder(new EmptyBorder(2, 2, 2, 2));
		txAcrescimo.setFormatterFactory(
				new DefaultFormatterFactory(criarFormatacaoDecimal("+")));  
		pnDescontoAcrescimo.add(txAcrescimo);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(2);
		pnDescontoAcrescimo.add(horizontalStrut_4);
		
		
		JPanel pnQuantidade = new JPanel();
		pnQuantidade.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 255), 2, true), 
				"Quantidade", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 255, 255)));
		pnQuantidade.setOpaque(false);
		
		JPanel pnItensVenda = new JPanel();
		pnItensVenda.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 255), 2, true), 
				"Itens da Venda", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 255, 255)));
		pnItensVenda.setOpaque(false);

		
		mudarFocoQuandoEnter(textField, txDesconto);
		mudarFocoQuandoEnter(txDesconto, txAcrescimo);
		mudarFocoQuandoEnter(txAcrescimo, textField);

		
		ItemVenda itemVenda = new ItemVenda(0L, BigDecimal.valueOf(20), 10, BigDecimal.valueOf(20));
		ItemVenda itemVenda2 = new ItemVenda(1L, BigDecimal.valueOf(30), 40, BigDecimal.valueOf(30));
		ItemVenda itemVenda3 = new ItemVenda(0L, BigDecimal.valueOf(20), 10, BigDecimal.valueOf(20));
		
		
		
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(pnItensVenda, GroupLayout.DEFAULT_SIZE, 1052, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(pnProduto, GroupLayout.PREFERRED_SIZE, 552, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(pnQuantidade, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(pnDescontoAcrescimo, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(btnFullScreen, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnFechar, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnFechar, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnFullScreen, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(pnProduto, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addComponent(pnDescontoAcrescimo, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addComponent(pnQuantidade, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(pnItensVenda, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
					.addGap(233))
		);
		
		
		
		
		
		
		
		
		contentPanel.setLayout(gl_contentPanel);

	}
	
	
	
	private JPanel configurarPanelEstilizado(JPanel panel, String titulo) {
		panel.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 255), 2, true), 
				titulo, TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel.setOpaque(false);
		return panel;
	}
	
	private JPanel configurarPanelEstilizado(String titulo) {
		return configurarPanelEstilizado(new JPanel(), titulo);
	}
	
	private NumberFormatter criarFormatacaoDecimal(String prefixo) {
		DecimalFormat dFormat = new DecimalFormat(prefixo + "#,##0.00");
	    NumberFormatter formatter = new NumberFormatter(dFormat);
	    formatter.setFormat(dFormat);
	    formatter.setAllowsInvalid(false);
	    return formatter;
	}
	
	private void mudarFocoQuandoEnter(JComponent origem, JComponent componenteEmFoco) {
		origem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					componenteEmFoco.requestFocus();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals(CMD_FECHAR))
			confirmarFecharPontoDeVendas();

		else if (e.getActionCommand().endsWith(CMD_FULLSCREEN))
			alternarParaTelaCheiaOuNormal();

	}

	private void confirmarFecharPontoDeVendas() {
		int opcaoSelecionada = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja fechar?");
		if (opcaoSelecionada == JOptionPane.YES_OPTION) {
			dispose();
		}
	}

	private void alternarParaTelaCheiaOuNormal() {

		if (getExtendedState() == JFrame.NORMAL)
			setExtendedState(MAXIMIZED_BOTH);
		else
			setExtendedState(NORMAL);

	}

	private ImageIcon icone24x24(String caminho) {
		return Utilitarios.mudarScala(Icones.getIcone(caminho), 24, 24);
	}
}
