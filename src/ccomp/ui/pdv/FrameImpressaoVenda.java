package ccomp.ui.pdv;



import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import ccomp.dominios.venda.ItemVenda;

public class FrameImpressaoVenda extends JDialog {

	private static final long serialVersionUID = 800968112957234975L;
	private final JPanel contentPanel = new JPanel();

	private ListagemItem listagemItem;
	
	public FrameImpressaoVenda( ListagemItem listagemItem ) {
		
		this.listagemItem = listagemItem;
		
		setTitle("Registro de venda");
		setType(Type.UTILITY);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(345, 447));
		setBounds(100, 100, 353, 499);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		getContentPane().add(scrollPane);

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		scrollPane.setViewportView(textArea);
		textArea.setText(construirDocumentoNaoFiscal());
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
		setModal(false);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setVisible(true);
	}


	private String construirDocumentoNaoFiscal() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(String.format("+-----------------+-----------------------+%n"));
		buffer.append(String.format("| Registro de venda                       |%n"));
		buffer.append(String.format("+-----------------+-----------------------+%n"));
		buffer.append(String.format("+-----------------+--------------+--------+%n"));
		buffer.append(String.format("| Nome            | Valor        | Qtde   |%n"));
		buffer.append(String.format("+---------------------+----------+--------+%n"));

		List<ItemVenda> totalizadorItens = new ArrayList<ItemVenda>();
		listagemItem.consumirItensDaListagem(totalizadorItens::add);
		
		totalizadorItens.forEach(itemVenda -> 
		{
			buffer.append(String.format("| %-15s | %-12s | %-6s |%n", 
					listagemItem.getCacheMetadataProdutos().get(itemVenda.getIdProduto())[0],
					itemVenda.getValorUnitario(), 
					itemVenda.getQuantidadeTotal()));
		});
		
		
		listagemItem.consumirItensDaListagem(itemVenda -> 
		{
			
		});
		
		buffer.append(String.format("+-----------------+--------------+--------+%n"));
		buffer.append(String.format("| Preço total: %-26s |%n",
				totalizadorItens.stream()
				.map(ItemVenda::getValorTotalItem)
				.reduce(BigDecimal.ZERO, BigDecimal::add).doubleValue())
				);
		buffer.append(String.format("+-----------------------------------------+%n"));
		return buffer.toString();
	}

}