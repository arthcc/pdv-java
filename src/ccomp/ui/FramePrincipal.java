package ccomp.ui;


import static ccomp.core.Icones.getIcone;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import ccomp.dominios.produto.Produto;
import ccomp.dominios.unidade.Unidade;
import ccomp.ui.pagamento.FrameCadastroPagamento;
import ccomp.ui.pdv.FrameHistoricoDeVendas;
import ccomp.ui.pdv.FramePontoDeVendas;
import ccomp.ui.produto.FrameCadastroProduto;
import ccomp.ui.produto.FrameLocalizadorProduto;
import ccomp.ui.unidade.FrameCadastroUnidade;

public class FramePrincipal extends JFrame {

	private static final long serialVersionUID = -4121999263437414993L;
	private JPanel contentPane;

	public FramePrincipal() {
		setResizable(false);
		
		setTitle("CCOMP Market 2023");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 676, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setMinimumSize(getSize());

		setContentPane(contentPane);
		
		JToolBar toolbarMaster = new JToolBar();
		toolbarMaster.setBounds(20, 10, 629, 213);
		toolbarMaster.setOpaque(false);
		toolbarMaster.setBorder(new EmptyBorder(10, 10, 10, 10));
		toolbarMaster.setFloatable(false);
		
		JToolBar toolbarPrincipal = new JToolBar();
		toolbarPrincipal.setOpaque(false);
		toolbarPrincipal.setOrientation(SwingConstants.VERTICAL);
		toolbarPrincipal.setMaximumSize(new Dimension(200, 200));
		toolbarPrincipal.setFloatable(false);
		toolbarPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		toolbarMaster.add(toolbarPrincipal);
		
		
		JButton btnPontoDeVendas = new JButton("PONTO DE VENDAS");
		btnPontoDeVendas.setOpaque(false);
		btnPontoDeVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FramePontoDeVendas.criarPontoDeVendas();
			}
		});
		btnPontoDeVendas.setMaximumSize(new Dimension(250, 100));
		toolbarPrincipal.add(btnPontoDeVendas);
		btnPontoDeVendas.setIcon(getIcone("/ccomp/icons/terminal-pos.png"));
		estilizarJButton(btnPontoDeVendas, true);
		
		Component verticalStrut_3 = Box.createVerticalStrut(2);
		toolbarPrincipal.add(verticalStrut_3);
		
		JButton btnHistoricoDeVendas = new JButton("HISTORICO DE VENDAS");
		btnHistoricoDeVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameHistoricoDeVendas.criarHistoricoDeVendas();
			}
		});
		btnHistoricoDeVendas.setIcon(getIcone("/ccomp/icons/terminal-pos.png"));
		btnHistoricoDeVendas.setOpaque(false);
		btnHistoricoDeVendas.setMaximumSize(new Dimension(250, 100));
		btnHistoricoDeVendas.setFocusPainted(false);
		btnHistoricoDeVendas.setBorder(new CompoundBorder(new LineBorder(new Color(200,200,200), 1, true), new EmptyBorder(15, 10, 15, 10)));
		toolbarPrincipal.add(btnHistoricoDeVendas);
		
		Component verticalStrut_2 = Box.createVerticalStrut(50);
		toolbarPrincipal.add(verticalStrut_2);
		
		
		Component horizontalStrut = Box.createHorizontalStrut(2);
		toolbarMaster.add(horizontalStrut);
		
		JToolBar toolbarProdutos = new JToolBar();
		toolbarProdutos.setOpaque(false);
		toolbarProdutos.setMaximumSize(new Dimension(200, 200));
		toolbarMaster.add(toolbarProdutos);
		toolbarProdutos.setOrientation(SwingConstants.VERTICAL);
		toolbarProdutos.setFloatable(false);
		toolbarProdutos.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		
		JButton btnCadastroDeProduto = new JButton("CADASTRO DE PRODUTO");
		btnCadastroDeProduto.setOpaque(false);

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
		btnLocalizarProduto.setOpaque(false);
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
		toolbarOutros.setOpaque(false);
		toolbarOutros.setMaximumSize(new Dimension(220, 200));
		toolbarOutros.setOrientation(SwingConstants.VERTICAL);
		toolbarOutros.setFloatable(false);
		toolbarOutros.setBorder(new EmptyBorder(10, 10, 10, 10));
		toolbarMaster.add(toolbarOutros);
		
		JButton btnFormasDePagamento = new JButton("FORMAS DE PAGAMENTO");
		btnFormasDePagamento.setOpaque(false);
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
		btnCadastroDeUnidade.setOpaque(false);
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
		separator.setBounds(647, 823, 2, 26);
		
		JTree tree = new JTree();
		tree.setShowsRootHandles(true);
		Guia.carregarGuiaEmArvore(tree);
		tree.setBounds(20, 229, 630, 521);
		
		expandAllNodes(tree, true);
		contentPane.setLayout(null);
		contentPane.add(toolbarMaster);
		contentPane.add(separator);
		contentPane.add(tree);
	
	}
	
	
    private static void expandAllNodes(JTree tree, boolean expand) {
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel().getRoot();
        expandAll(tree, new TreePath(root), expand);
    }

    private static void expandAll(JTree tree, TreePath parent, boolean expand) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) parent.getLastPathComponent();
        if (node.getChildCount() >= 0) {
            for (int i = 0; i < node.getChildCount(); i++) {
                TreePath path = parent.pathByAddingChild(node.getChildAt(i));
                expandAll(tree, path, expand);
            }
        }

        // Expansion or collapse
        if (expand) {
            tree.expandPath(parent);
        } else {
            tree.collapsePath(parent);
        }
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