package ccomp.ui.pdv;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

import ccomp.dominios.produto.GerenciadorProduto;
import ccomp.dominios.produto.Produto;
import ccomp.dominios.unidade.GerenciadorUnidade;
import ccomp.dominios.unidade.Unidade;
import ccomp.dominios.venda.ItemVenda;
import ccomp.facade.GerenciadorSistemaFacade;

public class ListagemItem extends JList<ItemVenda> implements ListCellRenderer<ItemVenda>, FocusListener {

	private static final Color COR_SELECAO = Color.decode("#a855f7").darker();

	private static final long serialVersionUID = -996544306373331509L;

	private DefaultListModel<ItemVenda> listagemModel = new DefaultListModel<ItemVenda>(); 
	private final Map<Long, String[]> cacheMetadataProdutos = new HashMap<Long, String[]>();

	private GerenciadorProduto gerenciadorProduto;
	private GerenciadorUnidade gerenciadorUnidade;

	{
		gerenciadorProduto = GerenciadorSistemaFacade.getInstancia()
				.getGerenciadorProduto();
		gerenciadorUnidade = GerenciadorSistemaFacade.getInstancia()
				.getGerenciadorUnidade();
	}

	public ListagemItem() {
		
		setOpaque(false);
		setCellRenderer(this);
		addFocusListener(this);;
		setModel(listagemModel);

		/* Item apenas para display do títulos */
		listagemModel.add(0, new ItemVendaDescricaoLista());
	}

	private String criarTextoItemVenda(ItemVenda itemVenda) {
		return " " + String.format("%s %s %s %s %s %s %s", 
				arrumarTamanho(String.format("%06d", itemVenda.getIdProduto()), 7),
				arrumarTamanho(
						cacheMetadataProdutos.get(itemVenda.getIdProduto())[0], 17),
				arrumarTamanho(String.format("%,.2f " + 
						cacheMetadataProdutos.get(itemVenda.getIdProduto())[1], 
						(double)itemVenda.getQuantidadeTotal()), 17),
				arrumarTamanho(String.format("R$ %,.2f", itemVenda.getValorUnitario().doubleValue()), 17),
				arrumarTamanho(String.format("- R$ %,.2f", itemVenda.getValorDesconto().doubleValue()), 17),
				arrumarTamanho(String.format("+ R$ %,.2f", itemVenda.getValorAcrescimo().doubleValue()), 17),
				arrumarTamanho(String.format("+ R$ %,.2f", itemVenda.getValorTotalItem().doubleValue()), 17))
		.trim();
	}


	private String criarTitulos() {
		return " " + String.format("%s %s %s %s %s %s %s", 
				arrumarTamanho("Código", 7),
				arrumarTamanho("Produto", 17),
				arrumarTamanho("Quantidade", 17),
				arrumarTamanho("Vl. Unitário", 17),
				arrumarTamanho("Vl. Desconto", 17),
				arrumarTamanho("Vl. Acréscimo", 17),
				arrumarTamanho("Vl. Total do Item", 18))
		.trim();
	}

	private String arrumarTamanho(String texto, int tamanhoMaximo) {
		if (texto.length() < tamanhoMaximo) {
			char[] complemento = new char[tamanhoMaximo - texto.length()];
			Arrays.fill(complemento, ' ');
			return String.format("%s%s", texto, new String(complemento));
		}
		return texto.substring(0, tamanhoMaximo - 1);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends ItemVenda> list, ItemVenda value, 
			int index,boolean isSelected, boolean cellHasFocus) 
	{
		JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont( Defaults.getFontePadraoDerivada(15) );
		
		if (value.hashCode() == -1) {
			label.setPreferredSize(new Dimension(30, 50));
			label.setText(criarTitulos());
			return label;
		}

		label.setText(criarTextoItemVenda(value));
		
		if (isSelected) {
			label.setBackground(COR_SELECAO);
			label.setOpaque(true);
		} else {
			label.setOpaque(false);
		}
		
		return label;
	}
	
	public void consumirItensDaListagem(Consumer<ItemVenda> consumer) {
		if (consumer != null) {
			ItemVenda itemVenda;
			Enumeration<ItemVenda> enmItemVenda = listagemModel.elements();
			while (enmItemVenda.hasMoreElements()) {
				itemVenda = enmItemVenda.nextElement();
				if (itemVenda.hashCode() != -1) {
					consumer.accept(itemVenda);
				}
			}
		}
	}

	private void criarMetadataProduto(ItemVenda itemVenda) {
		long idProduto = itemVenda.getIdProduto();
		Produto produto = gerenciadorProduto
				.encontrarProdutoPorId(idProduto)
				.get();
		Unidade unidade = gerenciadorUnidade
				.encontrarUnidadePorId(idProduto);

		cacheMetadataProdutos.putIfAbsent(idProduto, 
				new String[] { 
						produto.getNome().toUpperCase(), 
						unidade.getNome().toUpperCase() 
		});
	}

	public void adicionarItem(ItemVenda itemVenda) {
		criarMetadataProduto(itemVenda);
		listagemModel.addElement(itemVenda);
	}

	public void apagarInformacoesDeVenda() {
		listagemModel.removeAllElements();
		cacheMetadataProdutos.clear();
		revalidate();
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (listagemModel.size() > 1) {
			setSelectedIndex(1);
			repaint();
		}
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		clearSelection();
	}


}
