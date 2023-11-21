package ccomp.ui.pdv;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import ccomp.core.ui.VendaTabelaUI;
import ccomp.dominios.venda.GerenciadorVenda;
import ccomp.facade.GerenciadorSistemaFacade;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameHistoricoDeVendas extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private VendaTabelaUI tabelaDeVendas;

	private FrameHistoricoDeVendas() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModal(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		tabelaDeVendas = new VendaTabelaUI();
		tabelaDeVendas.carregarVendasDoSistema();
		contentPanel.add(tabelaDeVendas);
		
		JButton btnImprimirVenda = new JButton("Imprimir Venda Selecionada");
		btnImprimirVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				long idVenda = tabelaDeVendas.getIdVendaSelecionado();
				if (idVenda == -1L) {
					JOptionPane.showMessageDialog(null, "Selecione uma venda antes.", 
							"Erro ao imprimir", JOptionPane.WARNING_MESSAGE);
					return;
				}
				GerenciadorVenda gerenciadorVenda = GerenciadorSistemaFacade.getInstancia()
						.getGerenciadorVenda();
				new FrameImpressaoVenda(gerenciadorVenda.encontrarVendaPorId(idVenda), 
						GerenciadorSistemaFacade.getInstancia().getGerenciadorProduto());
			}
		});
		contentPanel.add(btnImprimirVenda, BorderLayout.SOUTH);
		setVisible(true);
	}

	public static void criarHistoricoDeVendas() {
		new FrameHistoricoDeVendas();
	}
}
