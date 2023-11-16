package ccomp.dominios.formaPagamento;

import static ccomp.core.Utilitarios.isNullOrEmpty;
import static ccomp.core.Utilitarios.validar;
import static ccomp.core.Utilitarios.validarNaoNulo;
import static ccomp.core.Utilitarios.validarNaoVazio;

import java.util.Collection;
import java.util.Optional;

import ccomp.dominios.pagamento.Pagamento;


public class GerenciadorTipoPagamento {
	
	private PagamentoRepositorio pagamentoRepositorio;

	private static final Pagamento PAGAMENTO_PADRAO = new Pagamento("Pix");

	public GerenciadorTipoPagamento() {
		pagamentoRepositorio = new PagamentoRepositorio();

		registrarPagamento(PAGAMENTO_PADRAO);
		registrarPagamento("Vale Alimentação");
		registrarPagamento("Vale Refeição");
		registrarPagamento("Cartão de Crédito");
		registrarPagamento("Cartão de Débito");

	}


	public void registrarPagamento(Pagamento pagamento) {

		validarNaoVazio((isNullOrEmpty(pagamento.getNome())) , "Nome de pagamento inválido");
		validar((pagamentoRepositorio.encontrarPorNome(pagamento.getNome())
				.isPresent()), "Nome já utilizado", "Nome diferente do já cadastrado");

		pagamentoRepositorio.criar(pagamento);
		
	}
	
	public void registrarPagamento(String nomePagamento) {
		registrarPagamento(new Pagamento(nomePagamento.trim()));
	}


	public void deletarUnidade(Pagamento pagamento) {
		validarNaoNulo(pagamento, "Pagamento");
		pagamentoRepositorio.deletar(pagamento);

	}

	public Collection<Pagamento> todos() {
		return pagamentoRepositorio.todos();
	}

	public Pagamento encontrarPagamentoPorId(Long id) {
		
		Optional<Pagamento> pagamentoOpt = pagamentoRepositorio.encontrarPorId(id);
		
		if (!pagamentoOpt.isPresent())
			return PAGAMENTO_PADRAO;

		return pagamentoOpt.get();
	}

	public Collection<Pagamento> obterTodosPagamentos() {
		return pagamentoRepositorio.todos();
	}
	
}


