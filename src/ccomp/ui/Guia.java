package ccomp.ui;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public final class Guia {

	@SuppressWarnings("serial")
	public static void carregarGuiaEmArvore(JTree tree) {
		tree.setModel(new DefaultTreeModel(
				new DefaultMutableTreeNode("Guia de utilização:") {
					{
						DefaultMutableTreeNode node_1;
						DefaultMutableTreeNode node_2;
						DefaultMutableTreeNode node_3;
						node_1 = new DefaultMutableTreeNode("Produtos:");
							node_2 = new DefaultMutableTreeNode("Menu > CADASTRO DE PRODUTOS");
								node_2.add(new DefaultMutableTreeNode("- Criar produto (Nome, Preço, Estoque e Unidade);"));
								node_2.add(new DefaultMutableTreeNode("- Localização e Visualização de produtos;"));
							node_1.add(node_2);
							node_2 = new DefaultMutableTreeNode("Menu > LOCALIZAR PRODUTO");
								node_2.add(new DefaultMutableTreeNode("- Localização e Visualização de produtos;"));
								node_2.add(new DefaultMutableTreeNode("- Edição de Produto (Nome, Preço, Estoque e Unidade);"));
								node_2.add(new DefaultMutableTreeNode("- Visualizar data da última venda;"));
								node_2.add(new DefaultMutableTreeNode("- Exclusão de Produto;"));
							node_1.add(node_2);
						add(node_1);
						node_1 = new DefaultMutableTreeNode("Tipo de pagamento:");
							node_2 = new DefaultMutableTreeNode("Menu > FORMA DE PAGAMENTO");
								node_2.add(new DefaultMutableTreeNode("- Criar Tipo de Pagamento (Nome);"));
								node_2.add(new DefaultMutableTreeNode("- Edição Tipo de Pagamento (Nome);"));
								node_2.add(new DefaultMutableTreeNode("- Exclusão Tipo de Pagamento;"));
							node_1.add(node_2);
						add(node_1);
						node_1 = new DefaultMutableTreeNode("Unidades:");
							node_2 = new DefaultMutableTreeNode("Menu > CADASTRO DE UNIDADES");
								node_2.add(new DefaultMutableTreeNode("- Criar Unidade (Nome);"));
								node_2.add(new DefaultMutableTreeNode("- Edição Unidade (Nome);"));
								node_2.add(new DefaultMutableTreeNode("- Exclusão Unidade;"));
							node_1.add(node_2);
						add(node_1);
						node_1 = new DefaultMutableTreeNode("Ponto de Vendas:");
							node_2 = new DefaultMutableTreeNode("Menu > PONTO DE VENDAS;");
								node_3 = new DefaultMutableTreeNode("- Criação de vendas");
									node_3.add(new DefaultMutableTreeNode("- Múltiplos itens de venda;"));
									node_3.add(new DefaultMutableTreeNode("- Seleção de forma de pagamento;"));
									node_3.add(new DefaultMutableTreeNode("- Seleção de desconto por item;"));
									node_3.add(new DefaultMutableTreeNode("- Seleção de acréscimo por item;"));
									node_3.add(new DefaultMutableTreeNode("- Finalização de venda e visualização de documento;"));
								node_2.add(node_3);
								node_3 = new DefaultMutableTreeNode("- Histórico de vendas");
									node_3.add(new DefaultMutableTreeNode("- Visualizar todas a vendas anteriores;"));
							node_2.add(node_3);
							node_1.add(node_2);
						add(node_1);
					}
				}
			));
	}
	
}
