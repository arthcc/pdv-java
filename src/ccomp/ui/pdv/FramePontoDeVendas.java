package ccomp.ui.pdv;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
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
import ccomp.dominios.produto.GerenciadorProduto;
import ccomp.dominios.produto.Produto;
import ccomp.dominios.venda.ItemVenda;
import ccomp.facade.GerenciadorSistemaFacade;
import ccomp.ui.produto.FrameLocalizadorProduto;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeListener;
import javax.swing.text.Caret;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.JTextComponent;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import org.omg.PortableInterceptor.LOCATION_FORWARD;

import javax.swing.border.LineBorder;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Component;

import javax.management.InstanceAlreadyExistsException;
import javax.swing.Box;
import javax.swing.JFormattedTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;

public class FramePontoDeVendas extends JFrame implements ActionListener {


	private static final long serialVersionUID = 2693925118715486463L;

	public static final String CMD_FECHAR = "fechar";
	public static final String CMD_FULLSCREEN = "fullscreen";
	public static final String CMD_LOCALIZAR = "localizar";

	
	private final GerenciadorProduto gerenciadorProduto;
	{
		gerenciadorProduto = GerenciadorSistemaFacade.getInstancia()
				.getGerenciadorProduto();
	}


	public static void main(String[] args) {

		try {
			GerenciadorSistemaFacade.criarGerenciadorSistema();
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException | InstanceAlreadyExistsException e) {
			e.printStackTrace();
		}
		FramePontoDeVendas pontoDeVendas = new FramePontoDeVendas();
		pontoDeVendas.setVisible(true);

	}

	private final CustomGradientPanel contentPanel;
	private JFormattedTextField txCodigoProduto;
	private JFormattedTextField txQuantidade;
	private JFormattedTextField txTotalVenda;
	private ListagemItem listagemItem;

	private FramePontoDeVendas() {

		/* Configuração propriedades do JFrame */

		setTitle("PDV");
		setUndecorated(true);
		setResizable(false);
		setBounds(100, 100, 1308, 746);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		/* Configuração propriedades do Panel de fundo */

		contentPanel = new CustomGradientPanel(Color.decode("#a855f7"), 
				Color.decode("#818cf8"));
		setContentPane(contentPanel);

		contentPanel.setBorder(new CompoundBorder(new LineBorder(contentPanel.getColorTop()
				.darker(), 2), new EmptyBorder(5, 5, 5, 5)));

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

		txCodigoProduto = new JFormattedTextField();
		NumberFormatter formatter = new NumberFormatter();
		formatter.setFormat(new DecimalFormat("0"));
		formatter.setAllowsInvalid(true);
		txCodigoProduto.setFont(new Font("Verdana", Font.BOLD, 30));
		txCodigoProduto.setOpaque(false);
		txCodigoProduto.setCaretColor(Color.WHITE);
		txCodigoProduto.setBorder(null);
		txCodigoProduto.setForeground(Color.WHITE);
		txCodigoProduto.setValue(new Double(0));
		txCodigoProduto.setFormatterFactory(new DefaultFormatterFactory(formatter));
		pnProduto.add(txCodigoProduto);
		txCodigoProduto.setColumns(10);

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
		
		JPanel pnTotalizador = new JPanel();
		pnTotalizador.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 255), 2, true), 
				"Total", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 255, 255)));
		pnTotalizador.setOpaque(false);
		
		JLabel lblNewLabel = new JLabel("CCOMP PDV");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel.setForeground(Color.WHITE);
		
		JPanel pnTipoPagamentos = new JPanel();
		pnTipoPagamentos.setOpaque(false);
		pnTipoPagamentos.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 255), 2, true), "Formas de Pagamento", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 255, 255)));



		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(pnTotalizador, GroupLayout.PREFERRED_SIZE, 571, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(pnTipoPagamentos, GroupLayout.DEFAULT_SIZE, 697, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addComponent(pnItensVenda, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addComponent(pnProduto, GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(pnQuantidade, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(pnDescontoAcrescimo, GroupLayout.PREFERRED_SIZE, 342, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 460, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 770, Short.MAX_VALUE)
							.addComponent(btnFullScreen, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnFechar, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(pnQuantidade, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(btnFechar, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnFullScreen, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
									.addGap(18))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(pnProduto, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addComponent(pnDescontoAcrescimo, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))))
					.addGap(18)
					.addComponent(pnItensVenda, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(pnTotalizador, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
						.addComponent(pnTipoPagamentos, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
					.addGap(148))
		);
		pnTipoPagamentos.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setPreferredSize(new Dimension(200, 50));
		btnNewButton.setFont(new Font("Verdana", Font.BOLD, 14));
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setOpaque(false);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBorder(new LineBorder(Color.WHITE, 2, true));
		
		pnTipoPagamentos.add(btnNewButton);
		pnTotalizador.setLayout(new BorderLayout(0, 0));
		
		txTotalVenda = new JFormattedTextField();
		txTotalVenda.setFocusable(false);
		txTotalVenda.setValue(0);
		txTotalVenda.setEditable(false);
		txTotalVenda.setOpaque(false);
		txTotalVenda.setHorizontalAlignment(SwingConstants.CENTER);
		txTotalVenda.setForeground(Color.WHITE);
		txTotalVenda.setFont(new Font("Verdana", Font.BOLD, 39));
		txTotalVenda.setCaretColor(Color.WHITE);
		txTotalVenda.setBorder(new EmptyBorder(2, 2, 2, 2));
		
		txTotalVenda.setFormatterFactory(
				new DefaultFormatterFactory(criarFormatacaoDecimal("R$ ")));
		
		pnTotalizador.add(txTotalVenda);
		pnQuantidade.setLayout(new BoxLayout(pnQuantidade, BoxLayout.X_AXIS));

		txQuantidade = new JFormattedTextField();
		txQuantidade.setOpaque(false);
		txQuantidade.setHorizontalAlignment(SwingConstants.CENTER);
		txQuantidade.setForeground(new Color(255, 102, 102));
		txQuantidade.setFont(new Font("Verdana", Font.BOLD, 28));
		txQuantidade.setCaretColor(new Color(255, 102, 102));
		txQuantidade.setBorder(new EmptyBorder(2, 2, 2, 2));
		txQuantidade.setValue(new Double(1));
		txQuantidade.setFormatterFactory(
				new DefaultFormatterFactory(criarFormatacaoDecimal("")));


		pnQuantidade.add(txQuantidade);
		pnItensVenda.setLayout(new BorderLayout(0, 0));

		listagemItem = new ListagemItem();

		pnItensVenda.add(listagemItem, BorderLayout.CENTER);

		mudarFocoQuandoEnter(txCodigoProduto, txQuantidade);
		
		JButton btnLocalizar = new JButton();
		btnLocalizar.setFocusable(false);
		btnLocalizar.setIcon(icone64x64("/ccomp/icons/pdv/localizar.png"));
		btnLocalizar.setActionCommand(CMD_LOCALIZAR);
		btnLocalizar.setContentAreaFilled(false);
		btnLocalizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLocalizar.setFocusPainted(false);
		btnLocalizar.addActionListener(this);
		btnLocalizar.setBorder(null);
		btnLocalizar.setBackground(null);
		btnLocalizar.setOpaque(false);
		pnProduto.add(btnLocalizar);
		
		Component horizontalStrut_1_1 = Box.createHorizontalStrut(3);
		pnProduto.add(horizontalStrut_1_1);
		mudarFocoQuandoEnter(txQuantidade, txDesconto);
		mudarFocoQuandoEnter(txDesconto, txAcrescimo);
		executarAcaoQuandoEnter(txAcrescimo, (v) -> 
		{

			ItemVenda itemVenda = new ItemVenda();
			itemVenda.setIdProduto(Long.valueOf(txCodigoProduto.getText()));
			itemVenda.setQuantidadeTotal((Double) txQuantidade.getValue());
			itemVenda.setValorAcrescimo(new BigDecimal((Double)txAcrescimo.getValue()));
			itemVenda.setValorDesconto(new BigDecimal((Double)txDesconto.getValue()));


			Produto produto = gerenciadorProduto.encontrarProdutoPorId(itemVenda.getIdProduto()).get();
			itemVenda.setValorUnitario(produto.getPreco());

			listagemItem.adicionarItem(itemVenda);
			txCodigoProduto.requestFocus();
			recalcularTotalizadorVenda();
			
		});
		contentPanel.setLayout(gl_contentPanel);

	}

	
	private void recalcularTotalizadorVenda() {
		List<BigDecimal> totalizadorItens = new ArrayList<BigDecimal>();
		listagemItem.consumirItensDaListagem(itemVenda -> 
			totalizadorItens.add(itemVenda.getValorTotalItem()));
		txTotalVenda.setValue(totalizadorItens
				.stream()
				.reduce(BigDecimal.ZERO, BigDecimal::add)
				.doubleValue());
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
		formatter.setValueClass(Double.class);
		formatter.setAllowsInvalid(false);
		return formatter;
	}

	private void executarAcaoQuandoEnter(JComponent origem, Consumer<Void> consumer) {
		if (consumer != null && origem != null) {
			origem.addKeyListener(new KeyAdapter() 
			{
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER)
						consumer.accept(null);
				}
			});
		}
	}


	private void mudarFocoQuandoEnter(JComponent origem, JComponent componenteParaFoco) {
		executarAcaoQuandoEnter(origem, (v) ->  
		{
			componenteParaFoco.requestFocus();
			if (componenteParaFoco instanceof JFormattedTextField) {
				((JFormattedTextField)componenteParaFoco).setText("");
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals(CMD_FECHAR))
			confirmarFecharPontoDeVendas();

		else if (e.getActionCommand().equals(CMD_FULLSCREEN))
			alternarParaTelaCheiaOuNormal();

		else if (e.getActionCommand().equals(CMD_LOCALIZAR)) {
			Produto produtoSelecionado = FrameLocalizadorProduto.localizarProduto();
			if (produtoSelecionado != null) {
				txCodigoProduto.setValue(produtoSelecionado.getId());
				txQuantidade.requestFocus();
			} else {
				JOptionPane.showMessageDialog(this, "Não produto encontrado no localizador de produto.");
			}
		}
		
	}

	private void confirmarFecharPontoDeVendas() {
		int opcaoSelecionada = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja fechar?");
		if (opcaoSelecionada == JOptionPane.YES_OPTION) {
			Runtime.getRuntime().exit(1);
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
	
	private ImageIcon icone64x64(String caminho) {
		return Utilitarios.mudarScala(Icones.getIcone(caminho), 64, 64);
	}
}
