package ccomp.facade;

import javax.management.InstanceAlreadyExistsException;

import ccomp.dominios.formaPagamento.GerenciadorTipoPagamento;
import ccomp.dominios.produto.GerenciadorProduto;
import ccomp.dominios.unidade.GerenciadorUnidade;
import ccomp.dominios.venda.GerenciadorVenda;

public class GerenciadorSistemaFacade {

	private static GerenciadorSistemaFacade sistemaInstancia;
	
	private GerenciadorProduto gerenciadorProduto;
	private GerenciadorVenda gerenciadorVenda;
	private GerenciadorTipoPagamento gerenciadorTipoPagamento;
	private GerenciadorUnidade gerenciadorUnidade;
	
	public static void criarGerenciadorSistema() throws InstanceAlreadyExistsException {
		
		if (sistemaInstancia != null)
			throw new InstanceAlreadyExistsException();
		
		new GerenciadorSistemaFacade();

	}
	
	private GerenciadorSistemaFacade() {
		criarTodosGerenciadores();
		sistemaInstancia = this;
	}
	
	
	private void criarTodosGerenciadores() {
		
		gerenciadorProduto = new GerenciadorProduto();
		gerenciadorVenda = new GerenciadorVenda();
		gerenciadorTipoPagamento = new GerenciadorTipoPagamento();
		gerenciadorUnidade = new GerenciadorUnidade();
		
	}
	
	public GerenciadorVenda getGerenciadorVenda() {
		return gerenciadorVenda;
	}
	
	public GerenciadorProduto getGerenciadorProduto() {
		return gerenciadorProduto;
	}
	
	public GerenciadorTipoPagamento getGerenciadorTipoPagamento() {
		return gerenciadorTipoPagamento;
	}
	
	public GerenciadorUnidade getGerenciadorUnidade() {
		return gerenciadorUnidade;
	}
	
	public static GerenciadorSistemaFacade getInstancia() {
		return sistemaInstancia;
	}
	
}
