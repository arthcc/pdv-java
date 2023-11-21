package ccomp.ui.pdv;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import ccomp.core.Utilitarios;
import ccomp.dominios.produto.GerenciadorProduto;
import ccomp.dominios.produto.Produto;
import ccomp.dominios.venda.ItemVenda;
import ccomp.dominios.venda.Venda;

public class FrameImpressaoVenda extends JDialog {

	private static final long serialVersionUID = 800968112957234975L;
	private final JPanel contentPanel = new JPanel();

	public FrameImpressaoVenda(Venda venda, GerenciadorProduto gerenciadorProduto) {
		setTitle("Registro de venda");
		setType(Type.UTILITY);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		setModal(true);
		setMinimumSize(new Dimension(400, 700));
		setBounds(100, 100, 400, 700);

		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		getContentPane().add(scrollPane);

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		scrollPane.setViewportView(textArea);


		StringBuilder builder = new StringBuilder();
		List<ItemVenda> totalizadorItens = venda.getItens();


		String format = "%1$-15s %2$-11s %3$-11s %3$-9s\n";

		builder.append("\n\n\n");
		builder.append(centerStr("UNIFESO", 50) + "\n");
		builder.append(centerStr("Campus Quinta do Paraíso", 50) + "\n");
		builder.append(centerStr("Estr. Venceslau José de Medeiros, 1045 - Prata", 50) + "\n");
		builder.append(centerStr("Teresópolis - RJ, 25976-345", 50) + "\n\n\n");

		builder.append(centerStr("ITENS", 50) + "\n\n");
		builder.append(String.format(format, "Produto", "Qt.", "Vl.Unitário", "Vl.Total"));
		totalizadorItens.forEach(e -> 
		{
			Produto produto = gerenciadorProduto.encontrarProdutoPorId(e.getIdProduto()).get();
			builder.append(String.format(format, 
					substringComLimite(produto.getNome(), 20), 
					formatarValor(e.getQuantidadeTotal()) + " x ", 
					"R$ " + formatarValor(e.getValorUnitario().doubleValue()), 
					"R$ " + formatarValor(e.getValorTotalItem().doubleValue())));
		});
		builder.append("\n\nForma de pagamento: " + venda.getPagamento().getNome());
		builder.append("\nData da venda: " + Utilitarios.dateToString(venda.getDataTempoVenda()));
		builder.append("\nEste não é um documento fiscal!");
		
		
		textArea.setText(builder.toString());	
		textArea.setFont(new Font("Courier New", Font.PLAIN, 12));
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setVisible(true);
	}

	private static String centerStr(String input, int length) {
		if (input.length() >= length) {
			return input;
		} else {
			int padding = length - input.length();
			int leftPadding = padding / 2;
			int rightPadding = padding - leftPadding;

			StringBuilder centeredString = new StringBuilder();
			for (int i = 0; i < leftPadding; i++) {
				centeredString.append(" ");
			}
			centeredString.append(input);
			for (int i = 0; i < rightPadding; i++) {
				centeredString.append(" ");
			}

			return centeredString.toString();
		}
	}

	private static String formatarValor(double valor) {
		return String.format("%.2f", valor);
	}

	private static String substringComLimite(String str, int limite) {
		if (str.length() > limite) {
			return str.substring(0, limite);
		} else {
			return str;
		}
	}

}