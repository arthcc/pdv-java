package ccomp.dominios.formaPagamento;


import static ccomp.core.Utilitarios.isNullOrEmpty;
import static ccomp.core.Utilitarios.validar;
import static ccomp.core.Utilitarios.validarNaoNulo;
import static ccomp.core.Utilitarios.validarNaoVazio;

import java.util.Collection;

import ccomp.core.Utilitarios;

public class GerenciadorTipoPagamento {
	
	private PagamentoRepositorio pagamentoRepositorio;

	private static final TipoPagamento PAGAMENTO_PADRAO = new TipoPagamento("Dinheiro");

	public GerenciadorTipoPagamento() {
		pagamentoRepositorio = new PagamentoRepositorio();
		registrarPagamento(PAGAMENTO_PADRAO);
	}


	public void registrarPagamento(TipoPagamento pagamento) {

		validarNaoVazio((isNullOrEmpty(pagamento.getNome())) , "Nome de pagamento inválido");
		validar((pagamentoRepositorio.encontrarPorNome(pagamento.getNome())
				.isPresent()), "Nome já utilizado", "Nome diferente do já cadastrado");

		pagamentoRepositorio.criar(pagamento);
		
	}
	
	public void registrarPagamento(String nomePagamento) {
		registrarPagamento(new TipoPagamento(nomePagamento.trim()));
	}


	public void deletarPagamento(TipoPagamento pagamento) {
		validarNaoNulo(pagamento, "Pagamento");
		pagamentoRepositorio.deletar(pagamento);
	}

	public void deletarPagamentoPorId(long idPagamento) {
		pagamentoRepositorio.deletarPorId(idPagamento);
	}
	
	public Collection<TipoPagamento> todos() {
		return pagamentoRepositorio.todos();
	}

	public TipoPagamento encontrarPagamentoPorId(Long idPagamento) {
		validarNaoNulo(idPagamento, "idPagamento");
		return pagamentoRepositorio.encontrarPorId(idPagamento)
				.orElse(null);
	}
	
	public TipoPagamento encontrarPagamentoPorNome(String nomePagamento) {
		validarNaoVazio(Utilitarios.isNullOrEmpty(nomePagamento) , 
				"Nome do pagamento não pode ser nulo ou vazio.");
		return pagamentoRepositorio.encontrarPorNome(nomePagamento)
				.orElse(null);
	}

	public Collection<TipoPagamento> obterTodosPagamentos() {
		return pagamentoRepositorio.todos();
	}
	
}


