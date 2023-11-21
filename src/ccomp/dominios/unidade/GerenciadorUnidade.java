package ccomp.dominios.unidade;

import static ccomp.core.Utilitarios.isNullOrEmpty;
import static ccomp.core.Utilitarios.validar;
import static ccomp.core.Utilitarios.validarNaoNulo;
import static ccomp.core.Utilitarios.validarNaoVazio;

import java.util.Collection;

import ccomp.core.Utilitarios;

public class GerenciadorUnidade {

	private UnidadeRepositorio unidadeRepositorio;

	private static final Unidade UNIDADE_PADRAO = new Unidade("UN");

	public GerenciadorUnidade() {
		unidadeRepositorio = new UnidadeRepositorio();
		registrarUnidade(UNIDADE_PADRAO);
		registrarUnidade("M2");
		registrarUnidade("M3");
		registrarUnidade("KG");
		registrarUnidade("G");
	}

	public void registrarUnidade(Unidade unidade) {
		validarNaoVazio((isNullOrEmpty(unidade.getNome())) , "Nome de unidade inválido");
		validar((unidadeRepositorio.encontrarPorNome(unidade.getNome())
				.isPresent()), "Nome já utilizado", "Nome diferente do já cadastrado");
		unidadeRepositorio.criar(unidade);
	}
	
	public void registrarUnidade(String nomeUnidade) {
		registrarUnidade(new Unidade(nomeUnidade.trim()));
	}

	public void deletarUnidade(Unidade unidade) {
		validarNaoNulo(unidade, "Unidade");
		unidadeRepositorio.deletar(unidade);
	}

	public void deletarUnidadePorId(Long idUnidade) {
		validarNaoNulo(idUnidade, "idUnidade");
		unidadeRepositorio.deletarPorId(idUnidade);
	}
	
	public Unidade encontrarUnidadePorId(Long id) {
		return unidadeRepositorio.encontrarPorId(id)
				.orElse(null);
	}
	
	public Unidade encontrarUnidadePorNome(String nomeUnidade) {
		validarNaoVazio(Utilitarios.isNullOrEmpty(nomeUnidade) , 
				"Nome da unidade não pode ser nulo ou vazio.");
		return unidadeRepositorio.encontrarPorNome(nomeUnidade)
				.orElse(null);
	}

	public Collection<Unidade> obterTodasUnidades() {
		return unidadeRepositorio.todos();
	}
	
}
