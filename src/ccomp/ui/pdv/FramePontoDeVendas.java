package ccomp.ui.pdv;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.JTextComponent;
import javax.swing.text.NumberFormatter;

import ccomp.core.Icones;
import ccomp.core.Utilitarios;
import ccomp.dominios.formaPagamento.GerenciadorTipoPagamento;
import ccomp.dominios.produto.GerenciadorProduto;
import ccomp.dominios.produto.Produto;
import ccomp.dominios.venda.ItemVenda;
import ccomp.facade.GerenciadorSistemaFacade;
import ccomp.ui.produto.FrameLocalizadorProduto;

public class FramePontoDeVendas extends JFrame implements ActionListener {


	private static final long serialVersionUID = 2693925118715486463L;

	public static final String CMD_FECHAR = "fechar";
	public static final String CMD_FULLSCREEN = "fullscreen";
	public static final String CMD_LOCALIZAR = "localizar";
	public static final String CMD_EXCLUIR_ITEM = "excluir-item";
	public static final String CMD_LIMPAR_ITENS = "limpar-itens";
	public static final String CMD_FINALIZAR_VENDA = "finalizar-venda";
	public static final String CMD_CONFIG = "config";
	
	public static final KeyStroke VK_DELETE = KeyStroke.getKeyStroke(
			KeyEvent.VK_DELETE, 
			KeyEvent.VK_UNDEFINED);
	
	private final GerenciadorProduto gerenciadorProduto;
	{
		gerenciadorProduto = GerenciadorSistemaFacade.getInstancia()
				.getGerenciadorProduto();
	}


	/*
	 * APENAS PARA TESTE RAPIDO
	 * public static void main(String[] args) {

		try {
			GerenciadorSistemaFacade.criarGerenciadorSistema();
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException | InstanceAlreadyExistsException e) {
			e.printStackTrace();
		}
		FramePontoDeVendas pontoDeVendas = new FramePontoDeVendas();
		pontoDeVendas.setVisible(true);

	}*/

	private final CustomGradientPanel contentPanel;
	private JFormattedTextField txCodigoProduto;
	private JFormattedTextField txQuantidade;
	private JFormattedTextField txTotalVenda;
	private ListagemItem listagemItem;
	private JPanel pnTipoPagamentos;
	private JButton btnExcluirItem;
	private JButton btnLimparItens;
	private JPanel pnEdicao;
	private JPanel pnItensVenda;
	private JFormattedTextField txDesconto;
	private JFormattedTextField txAcrescimo;
	private JPanel pnQuantidade;
	private JButton btnLocalizar;
	private JPanel pnProduto;
	private JPanel pnTotalizador;
	private JButton btnFechar;
	private JButton btnFullScreen;
	private JLabel lblTitulo;
	private JLabel lblLogoImagem;
	private JButton btnConfig;


	private FramePontoDeVendas() {

		/* Configuração propriedades do JFrame */

		setTitle("PDV");
		setUndecorated(true);
		setBounds(100, 100, 1180, 825);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		/* Configuração propriedades do Panel de fundo */
		

		contentPanel = new CustomGradientPanel(Color.decode("#a855f7"), 
				Color.decode("#818cf8"));
		setContentPane(contentPanel);

		contentPanel.setBorder(new CompoundBorder(new LineBorder(contentPanel.getColorTop()
				.darker(), 2), new EmptyBorder(5, 5, 5, 5)));

		setLocationRelativeTo(null);

		/* components */
		
		btnFechar = new JButton();
		btnFechar.setIcon(icone24x24("/ccomp/icons/pdv/fechar.png"));
		btnFechar.setActionCommand(CMD_FECHAR);
		btnFechar.setContentAreaFilled(false);
		btnFechar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFechar.setFocusPainted(false);
		btnFechar.addActionListener(this);
		btnFechar.setBorder(null);
		btnFechar.setBackground(null);
		btnFechar.setOpaque(false);

		btnFullScreen = new JButton();
		btnFullScreen.setIcon(icone24x24("/ccomp/icons/pdv/fullscreen.png"));
		btnFullScreen.setActionCommand(CMD_FULLSCREEN);
		btnFullScreen.setContentAreaFilled(false);
		btnFullScreen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFullScreen.setFocusPainted(false);
		btnFullScreen.addActionListener(this);
		btnFullScreen.setBorder(null);
		btnFullScreen.setBackground(null);
		btnFullScreen.setOpaque(false);

		pnProduto = new JPanel();
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

		txDesconto = new JFormattedTextField();
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

		txAcrescimo = new JFormattedTextField();
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


		pnQuantidade = new JPanel();
		/* REFATORAR DEPOIS DE FINALIZAR A INTERFACE, WINDOWS BUILDER NÃO FUNCIONA DIREITO GENERAZIANDO BORDERS*/
		pnQuantidade.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 255), 2, true), 
				"Quantidade", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 255, 255)));
		pnQuantidade.setOpaque(false);

		pnItensVenda = new JPanel();
		/* REFATORAR DEPOIS DE FINALIZAR A INTERFACE, WINDOWS BUILDER NÃO FUNCIONA DIREITO GENERAZIANDO BORDERS*/
		pnItensVenda.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 255), 2, true), 
				"Itens da Venda", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 255, 255)));
		pnItensVenda.setOpaque(false);

		pnTotalizador = new JPanel();
		/* REFATORAR DEPOIS DE FINALIZAR A INTERFACE, WINDOWS BUILDER NÃO FUNCIONA DIREITO GENERAZIANDO BORDERS*/
		pnTotalizador.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 255), 2, true), 
				"Total", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 255, 255)));
		pnTotalizador.setOpaque(false);

		lblTitulo = new JLabel("Ciência da Computação - PDV");
		lblTitulo.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 18));
		lblTitulo.setForeground(Color.WHITE);

		pnTipoPagamentos = new JPanel();
		pnTipoPagamentos.setOpaque(false);
		pnTipoPagamentos.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 255), 2, true),
				"Formas de Pagamento", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 255, 255)));

		lblLogoImagem = new JLabel("");
		lblLogoImagem.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogoImagem.setIcon(Icones.getIcone("/ccomp/icons/pdv/unifeso-logo.png"));
		
		pnEdicao = new JPanel();
		pnEdicao.setOpaque(false);
		pnEdicao.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 255), 2, true),
				"Edi\u00E7\u00E3o", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 255, 255)));
		pnEdicao.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnFinalizarVenda = new JButton("FINALIZAR VENDA");
		btnFinalizarVenda.addActionListener(this);
		btnFinalizarVenda.setIcon(icone24x24("/ccomp/icons/pdv/vender.png"));
		btnFinalizarVenda.setActionCommand(CMD_FINALIZAR_VENDA);
		btnFinalizarVenda.setVerticalTextPosition(SwingConstants.CENTER);
		btnFinalizarVenda.setPreferredSize(new Dimension(50, 50));
		btnFinalizarVenda.setOpaque(false);
		btnFinalizarVenda.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnFinalizarVenda.setForeground(Color.WHITE);
		btnFinalizarVenda.setFont(new Font("Verdana", Font.BOLD, 20));
		btnFinalizarVenda.setFocusable(false);
		btnFinalizarVenda.setContentAreaFilled(false);
		btnFinalizarVenda.setBorder(new LineBorder(Color.WHITE, 2, true));
		adicionarAnimacaoMouseBtn(btnFinalizarVenda);
		pnTipoPagamentos.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnConfig = new JButton();
		btnConfig.setIcon(icone24x24("/ccomp/icons/pdv/config.png"));
		btnConfig.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnConfig.setOpaque(false);
		btnConfig.addActionListener(this);
		btnConfig.setActionCommand(CMD_CONFIG);
		btnConfig.setFocusPainted(false);
		btnConfig.setContentAreaFilled(false);
		btnConfig.setBorder(null);
		btnConfig.setBackground((Color) null);
		
	
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblLogoImagem, GroupLayout.DEFAULT_SIZE, 1154, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblTitulo, GroupLayout.PREFERRED_SIZE, 460, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 628, Short.MAX_VALUE)
							.addComponent(btnConfig, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnFullScreen, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnFechar, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(pnItensVenda, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(pnEdicao, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(pnProduto, GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(pnQuantidade, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(pnDescontoAcrescimo, GroupLayout.PREFERRED_SIZE, 342, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(pnTotalizador, GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(pnTipoPagamentos, GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(btnFinalizarVenda, GroupLayout.DEFAULT_SIZE, 1154, Short.MAX_VALUE)
							.addContainerGap())))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
									.addComponent(btnFechar, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnFullScreen, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
								.addGap(18))
							.addComponent(lblTitulo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(btnConfig, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblLogoImagem, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(pnProduto, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addComponent(pnQuantidade, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addComponent(pnDescontoAcrescimo, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(pnEdicao, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
						.addComponent(pnItensVenda, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(pnTotalizador, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(pnTipoPagamentos, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnFinalizarVenda, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
		);
		
		btnExcluirItem = new JButton("Excluir");
		btnExcluirItem.addActionListener(this);
		btnExcluirItem.setActionCommand(CMD_EXCLUIR_ITEM);
		btnExcluirItem.setPreferredSize(new Dimension(50, 50));
		btnExcluirItem.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnExcluirItem.setHorizontalTextPosition(SwingConstants.CENTER);
		btnExcluirItem.setFont(new Font("Verdana", Font.BOLD, 9));
		btnExcluirItem.setContentAreaFilled(false);
		btnExcluirItem.setOpaque(false);
		btnExcluirItem.setForeground(Color.WHITE);
		btnExcluirItem.setBorder(new LineBorder(Color.WHITE, 2, true));
		btnExcluirItem.setFocusable(false);
		
		btnExcluirItem.setIcon(icone24x24("/ccomp/icons/pdv/remover.png"));
		adicionarAnimacaoMouseBtn(btnExcluirItem);
		pnEdicao.add(btnExcluirItem);
		
		btnLimparItens = new JButton("Limpar");
		btnLimparItens.addActionListener(this);
		btnLimparItens.setIcon(icone24x24("/ccomp/icons/pdv/limpar-refresh.png"));
		btnLimparItens.setActionCommand(CMD_LIMPAR_ITENS);
		btnLimparItens.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnLimparItens.setPreferredSize(new Dimension(50, 50));
		btnLimparItens.setOpaque(false);
		btnLimparItens.setHorizontalTextPosition(SwingConstants.CENTER);
		btnLimparItens.setForeground(Color.WHITE);
		btnLimparItens.setFont(new Font("Verdana", Font.BOLD, 9));
		btnLimparItens.setFocusable(false);
		btnLimparItens.setContentAreaFilled(false);
		btnLimparItens.setBorder(new LineBorder(Color.WHITE, 2, true));
		adicionarAnimacaoMouseBtn(btnLimparItens);
		pnEdicao.add(btnLimparItens);
		pnTipoPagamentos.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));


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
		txQuantidade.setForeground(Color.white);
		txQuantidade.setFont(new Font("Verdana", Font.BOLD, 28));
		txQuantidade.setCaretColor(Color.white);
		txQuantidade.setBorder(new EmptyBorder(2, 2, 2, 2));
		txQuantidade.setValue(new Double(1));
		txQuantidade.setFormatterFactory(
				new DefaultFormatterFactory(criarFormatacaoDecimal("")));


		pnQuantidade.add(txQuantidade);
		pnItensVenda.setLayout(new BorderLayout(0, 0));

		listagemItem = new ListagemItem();
		listagemItem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if (e.getKeyCode() == KeyEvent.VK_DELETE) 
					excluirApenasItemSelecionado();
				
			}
		});

		pnItensVenda.add(listagemItem, BorderLayout.CENTER);

		mudarFocoQuandoEnter(txCodigoProduto, txQuantidade);

		btnLocalizar = new JButton();
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

			recalcularEVoltarReiniciarFocus();

		});
		contentPanel.setLayout(gl_contentPanel);


		GerenciadorTipoPagamento gerenciadorTipoPagamento = GerenciadorSistemaFacade.getInstancia()
				.getGerenciadorTipoPagamento();

		gerenciadorTipoPagamento.todos()
		.forEach(pagamento -> 
		{
			JButton btnFormaDePagamento = new JButton(pagamento.getNome());
			btnFormaDePagamento.setPreferredSize(new Dimension(200, 50));
			btnFormaDePagamento.setFont(new Font("Verdana", Font.BOLD, 14));
			btnFormaDePagamento.setContentAreaFilled(false);
			btnFormaDePagamento.setOpaque(false);
			btnFormaDePagamento.setForeground(Color.WHITE);
			btnFormaDePagamento.setBorder(new LineBorder(Color.WHITE, 2, true));
			btnFormaDePagamento.setFocusable(false);
			adicionarAnimacaoMouseBtn(btnFormaDePagamento);
			pnTipoPagamentos.add(btnFormaDePagamento);
			
		});


		adicionarEfeitoEmTextField(txCodigoProduto);
		adicionarEfeitoEmTextField(txQuantidade);
		adicionarEfeitoEmTextField(txAcrescimo);
		adicionarEfeitoEmTextField(txDesconto);

	}
	
	public static void criarPontoDeVendas() {
		FramePontoDeVendas pontoDeVendas = new FramePontoDeVendas();
		pontoDeVendas.setVisible(true);
	}
	
	private void excluirApenasItemSelecionado() {
		if (listagemItem.getSelectedIndex() != -1) {
			listagemItem.removerSelecionado();
			recalcularEVoltarReiniciarFocus();
		} else {
			JOptionPane.showMessageDialog(this, "Selecione um item para excluir!");
		}
	}
	
	
	/* EXPERIMENTAL PORRA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! */
	private void adicionarEfeitoEmTextField(JTextComponent component) {

		component.addFocusListener(new FocusAdapter() {
			
			Thread animacaoThread;
			volatile boolean emFoco = false;
			
			@Override
			public void focusGained(FocusEvent e) {
				emFoco = true;
				component.setText("");
				/* vai dar merda uma hora */
				if (animacaoThread != null)
					animacaoThread.interrupt();
				
				if (animacaoThread == null) {
					animacaoThread = new Thread(new Runnable() {
						@Override
						public void run() {
							for (int i = 0; i < 2 && emFoco; i++) {
								try {
									component.setBorder(new MatteBorder(0, 0, 3, 0, Color.BLACK));
									Thread.sleep(100L);
									component.setBorder(new MatteBorder(0, 0, 3, 0, contentPanel.getColorTop()));
									Thread.sleep(100L);
								} catch (InterruptedException e) {/* ignorar */}	
								component.setBorder(null);
							}
						}
					});
					animacaoThread.start();
				}
				super.focusGained(e);
			}

			@Override
			public void focusLost(FocusEvent e) {
				emFoco = false;
				component.setBorder(new EmptyBorder(2, 2, 2, 2));
				if (animacaoThread != null) {
					animacaoThread.interrupt();
					animacaoThread = null;
				}

				
				super.focusLost(e);
			}

		});

	}

	/* MELHORAR DEPOIS */
	private void adicionarAnimacaoMouseBtn(JButton button) {

		button.addMouseListener(new MouseAdapter() {

			final Color originalForeground = button.getForeground();

			@Override
			public void mouseEntered(MouseEvent e) {
				button.setOpaque(true);
				button.setBackground(Color.WHITE);
				button.setForeground(contentPanel.getColorBottom());
			}

			@Override
			public void mouseExited(MouseEvent e) {
				button.setOpaque(false);
				button.setBackground(Color.WHITE);
				button.setForeground(originalForeground);
			}

		});

	}

	@Override
	/* MELHORAR DEPOIS */
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals(CMD_FECHAR)) 
		{
			confirmarFecharPontoDeVendas();
		}
		else if (e.getActionCommand().equals(CMD_FULLSCREEN)) 
		{
			alternarParaTelaCheiaOuNormal();
		}
		else if (e.getActionCommand().equals(CMD_LOCALIZAR)) 
		{
			Produto produtoSelecionado = FrameLocalizadorProduto.localizarProduto();
			if (produtoSelecionado != null) {
				txCodigoProduto.setValue(produtoSelecionado.getId());
				txQuantidade.requestFocus();
			} else {
				JOptionPane.showMessageDialog(this, "Não produto encontrado no localizador de produto.");
			}
		}
		
		else if (e.getActionCommand().equals(CMD_EXCLUIR_ITEM)) 
		{
			excluirApenasItemSelecionado();
		}
		
		else if (e.getActionCommand().equals(CMD_LIMPAR_ITENS)) 
		{
			int confirmacao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir todos os itens?");
			if (confirmacao == JOptionPane.YES_OPTION) {
				listagemItem.apagarInformacoesDeVenda();
				recalcularTotalizadorVenda();
			}
		}
		else if (e.getActionCommand().equals(CMD_CONFIG))
		{
			FrameConfiguracao configuracao = new FrameConfiguracao(this);
			configuracao.setVisible(true);
		}

	}

	/* GENERALIZAR TOTALIZADOR, FrameImpressaoVenda TAMBÉM FAZ ESSA CONTA */
	private void recalcularTotalizadorVenda() {
		List<BigDecimal> totalizadorItens = new ArrayList<BigDecimal>();
		listagemItem.consumirItensDaListagem(itemVenda -> 
		totalizadorItens.add(itemVenda.getValorTotalItem()));
		txTotalVenda.setValue(totalizadorItens
				.stream()
				.reduce(BigDecimal.ZERO, BigDecimal::add)
				.doubleValue());
	}

	/* SERA USADO PARA REFATORAR O CÓDIGO, NÃO MEXER */
	private JPanel configurarPanelEstilizado(JPanel panel, String titulo) {
		panel.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 255), 2, true), 
				titulo, TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel.setOpaque(false);
		return panel;
	}

	/* SERA USADO PARA REFATORAR O CÓDIGO, NÃO MEXER */
	@SuppressWarnings("unused")
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

	private void recalcularEVoltarReiniciarFocus() {
		txCodigoProduto.requestFocus();
		recalcularTotalizadorVenda();
		restarTodosValores();
	}

	public void atualizarSkinFrame(Paleta paleta) {
		contentPanel.setColorTop(paleta.getTopCor());
		contentPanel.setColorBottom(paleta.getBottomCor());
		txAcrescimo.setForeground(paleta.getAcrescimoCor());
		txDesconto.setForeground(paleta.getDescontoCor());
		contentPanel.setBorder(new CompoundBorder(new LineBorder(contentPanel.getColorTop()
				.darker(), 2), new EmptyBorder(5, 5, 5, 5)));
		listagemItem.setSelecaoCor(contentPanel.getColorBottom().darker());

		repaint();
	}
	
	private void restarTodosValores() {
		txAcrescimo.setValue(0D);
		txDesconto.setValue(0D);
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
			if (componenteParaFoco instanceof JFormattedTextField)
				((JFormattedTextField)componenteParaFoco).setText("");
		});
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
	/* QUE BOSTA, MOVER PARA "Icones#getIcon" */
	private ImageIcon icone24x24(String caminho) {
		return Utilitarios.mudarScala(Icones.getIcone(caminho), 24, 24);
	}

	private ImageIcon icone64x64(String caminho) {
		return Utilitarios.mudarScala(Icones.getIcone(caminho), 64, 64);
	}

}
