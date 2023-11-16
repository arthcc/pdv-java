package ccomp.ui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import ccomp.dominios.produto.Produto;
import ccomp.dominios.unidade.Unidade;
import ccomp.ui.produto.FrameCadastroProduto;
import ccomp.ui.produto.FrameLocalizadorProduto;
import ccomp.ui.unidade.FrameCadastroUnidade;
import ccomp.ui.pagamento.FrameCadastroPagamento;
import ccomp.ui.pdv.FramePontoDeVendas;

import javax.swing.border.CompoundBorder;
import javax.swing.JSeparator;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import static ccomp.core.Icones.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FramePrincipal extends JFrame {

	private static final long serialVersionUID = -4121999263437414993L;
	private JPanel contentPane;

	public FramePrincipal() {
		
		setTitle("CCOMP Market 2023");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 676, 719);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setMinimumSize(getSize());

		setContentPane(contentPane);
		
		JToolBar toolbarMaster = new JToolBar();
		toolbarMaster.setBorder(new EmptyBorder(10, 10, 10, 10));
		toolbarMaster.setFloatable(false);
		
		JToolBar toolbarPrincipal = new JToolBar();
		toolbarPrincipal.setOrientation(SwingConstants.VERTICAL);
		toolbarPrincipal.setMaximumSize(new Dimension(200, 200));
		toolbarPrincipal.setFloatable(false);
		toolbarPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		toolbarMaster.add(toolbarPrincipal);
		
		
		JButton btnPontoDeVendas = new JButton("PONTO DE VENDAS");
		btnPontoDeVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FramePontoDeVendas.criarPontoDeVendas();
			}
		});
		btnPontoDeVendas.setMaximumSize(new Dimension(250, 100));
		toolbarPrincipal.add(btnPontoDeVendas);
		btnPontoDeVendas.setIcon(getIcone("/ccomp/icons/terminal-pos.png"));
		estilizarJButton(btnPontoDeVendas, true);
		
		JLabel lblTitle = new JLabel("MARKET - CCOMP");
		lblTitle.setMaximumSize(new Dimension(250, 100));
		lblTitle.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblTitle.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setIcon(getIcone("/ccomp/icons/celular.png"));
		toolbarPrincipal.add(lblTitle);
		
		
		Component horizontalStrut = Box.createHorizontalStrut(2);
		toolbarMaster.add(horizontalStrut);
		
		JToolBar toolbarProdutos = new JToolBar();
		toolbarProdutos.setMaximumSize(new Dimension(200, 200));
		toolbarMaster.add(toolbarProdutos);
		toolbarProdutos.setOrientation(SwingConstants.VERTICAL);
		toolbarProdutos.setFloatable(false);
		toolbarProdutos.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		
		
		JButton btnCadastroDeProduto = new JButton("CADASTRO DE PRODUTO");

		btnCadastroDeProduto.setMaximumSize(new Dimension(250, 100));
		toolbarProdutos.add(btnCadastroDeProduto);
		btnCadastroDeProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = FrameCadastroProduto.executarCadastroProduto();
				if (produto != null) {
					System.out.println(produto);	
					return;
				}
			JOptionPane.showMessageDialog(null, "Um produto não foi cadastrado", 
					"Alerta", JOptionPane.WARNING_MESSAGE);
				
			}
		});
		btnCadastroDeProduto.setIcon(getIcone("/ccomp/icons/adicionar.png"));
		estilizarJButton(btnCadastroDeProduto, true);
		
		JButton btnLocalizarProduto = new JButton("LOCALIZAR PRODUTO");
		btnLocalizarProduto.setMaximumSize(new Dimension(250, 50));
		btnLocalizarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameLocalizadorProduto.executarLocalizadorDeProduto();
			}
		});
		
		Component verticalStrut = Box.createVerticalStrut(2);
		toolbarProdutos.add(verticalStrut);
		btnLocalizarProduto.setIcon(getIcone("/ccomp/icons/lupa.png"));
		estilizarJButton(btnLocalizarProduto, false);
		toolbarProdutos.add(btnLocalizarProduto);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(2);
		toolbarMaster.add(horizontalStrut_1);
		
		JToolBar toolbarOutros = new JToolBar();
		toolbarOutros.setMaximumSize(new Dimension(220, 200));
		toolbarOutros.setOrientation(SwingConstants.VERTICAL);
		toolbarOutros.setFloatable(false);
		toolbarOutros.setBorder(new EmptyBorder(10, 10, 10, 10));
		toolbarMaster.add(toolbarOutros);
		
		JButton btnFormasDePagamento = new JButton("FORMAS DE PAGAMENTO");
		btnFormasDePagamento.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameCadastroPagamento cadastroPagamento = new FrameCadastroPagamento();
				cadastroPagamento.setVisible(true);
			}
		});
		btnFormasDePagamento.setMaximumSize(new Dimension(220, 40));
		toolbarOutros.add(btnFormasDePagamento);
		btnFormasDePagamento.setIcon(getIcone("/ccomp/icons/money-bag.png"));
		estilizarJButton(btnFormasDePagamento, false);
		
		Component verticalStrut_1 = Box.createVerticalStrut(2);
		toolbarOutros.add(verticalStrut_1);
		
		JButton btnCadastroDeUnidade = new JButton("CADASTRO DE UNIDADES");
		btnCadastroDeUnidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Unidade unidade = FrameCadastroUnidade.executarCadastroUnidade();
				if (unidade != null) {
					System.out.println(unidade);	
					return;
				}
			JOptionPane.showMessageDialog(null, "Uma unidade não foi cadastrado", 
					"Alerta", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		btnCadastroDeUnidade.setMaximumSize(new Dimension(220, 40));
		toolbarOutros.add(btnCadastroDeUnidade);
		btnCadastroDeUnidade.setIcon(getIcone("/ccomp/icons/fita-metrica.png"));
		estilizarJButton(btnCadastroDeUnidade, false);
		
		JSeparator separator = new JSeparator();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(toolbarMaster, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
						.addComponent(separator, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
					.addGap(11))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(toolbarMaster, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(430, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void estilizarJButton(JButton btn, boolean top) {
		if (top) {
			btn.setVerticalTextPosition(SwingConstants.BOTTOM);
			btn.setHorizontalTextPosition(SwingConstants.CENTER);
		} else {
			btn.setVerticalTextPosition(SwingConstants.CENTER);
			btn.setHorizontalTextPosition(SwingConstants.LEADING);
		}
		btn.setFocusPainted(false);
		btn.setBorder(new CompoundBorder(new LineBorder(new Color(200,200,200), 1, true), new EmptyBorder(15, 10, 15, 10)));
	}
}