package ccomp.dominios.produto;


import static ccomp.core.Utilitarios.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

import static ccomp.core.Utilitarios.randomInt;

public class GerenciadorProduto {

	private ProdutoRepositorio produtoRepositorio;

	public GerenciadorProduto() 
	{
		produtoRepositorio = new ProdutoRepositorio();
		
		getRepositorio().criar(new Produto("CAMISA VERDE", BigDecimal.valueOf(randomInt(1, 1000)), randomInt(200, 5000), null, 0L));
		getRepositorio().criar(new Produto("CAMISA AMARELA", BigDecimal.valueOf(randomInt(1, 1000)), randomInt(200, 5000), null, 0L));
		getRepositorio().criar(new Produto("CAMISA VERMELHA", BigDecimal.valueOf(randomInt(1, 1000)), randomInt(200, 5000), null, 0L));
		getRepositorio().criar(new Produto("CAMISA PRETA", BigDecimal.valueOf(randomInt(1, 1000)), randomInt(200, 5000), null, 0L));
		getRepositorio().criar(new Produto("CAMISA BRANCA", BigDecimal.valueOf(randomInt(1, 1000)), randomInt(200, 5000), null, 0L));
		getRepositorio().criar(new Produto("CAMISA JEANS", BigDecimal.valueOf(randomInt(1, 1000)), randomInt(200, 5000), null, 0L));
		getRepositorio().criar(new Produto("CAMISA ROSA", BigDecimal.valueOf(randomInt(1, 1000)), randomInt(200, 5000), null, 0L));
		
		getRepositorio().criar(new Produto("SHORT VERDE", BigDecimal.valueOf(randomInt(1, 1000)), randomInt(200, 5000), null, 0L));
		getRepositorio().criar(new Produto("SHORT BRANCO", BigDecimal.valueOf(randomInt(1, 1000)), randomInt(200, 5000), null, 0L));
		getRepositorio().criar(new Produto("SHORT JEANS", BigDecimal.valueOf(randomInt(1, 1000)), randomInt(200, 5000), null, 0L));
		getRepositorio().criar(new Produto("SHORT PRETO", BigDecimal.valueOf(randomInt(1, 1000)), randomInt(200, 5000), null, 0L));
		
		getRepositorio().criar(new Produto("CAPACETE VERDE", BigDecimal.valueOf(randomInt(1, 1000)), randomInt(200, 5000), null, 0L));
		getRepositorio().criar(new Produto("CADERNO AZUL", BigDecimal.valueOf(randomInt(1, 1000)), randomInt(200, 5000), null, 0L));
		getRepositorio().criar(new Produto("LAPIS BIC", BigDecimal.valueOf(randomInt(1, 1000)), randomInt(200, 5000), null, 0L));
		getRepositorio().criar(new Produto("CANETA BIC AZUL", BigDecimal.valueOf(randomInt(1, 1000)), randomInt(200, 5000), null, 0L));
		getRepositorio().criar(new Produto("CANETA BIC VERDE", BigDecimal.valueOf(randomInt(1, 1000)), randomInt(200, 5000), null, 0L));
		getRepositorio().criar(new Produto("CANETA BIC VERMELHO", BigDecimal.valueOf(randomInt(1, 1000)), randomInt(200, 5000), null, 0L));
		getRepositorio().criar(new Produto("CADERNO SKATE", BigDecimal.valueOf(randomInt(1, 1000)), randomInt(200, 5000), null, 0L));
		getRepositorio().criar(new Produto("CANETA PYTHON", BigDecimal.valueOf(randomInt(1, 1000)), randomInt(200, 5000), null, 0L));
		
		getRepositorio().criar(new Produto("BORRACHA", BigDecimal.valueOf(randomInt(1, 1000)), randomInt(200, 5000), null, 0L));
		getRepositorio().criar(new Produto("ESTOJO PRETO", BigDecimal.valueOf(randomInt(1, 1000)), randomInt(200, 5000), null, 0L));
		getRepositorio().criar(new Produto("LIVRO CLEAN CODE?", BigDecimal.valueOf(randomInt(1, 1000)), randomInt(200, 5000), null, 0L));
		getRepositorio().criar(new Produto("TO SEM IDEIA JÁ", BigDecimal.valueOf(randomInt(1, 1000)), randomInt(200, 5000), null, 0L));
		
		getRepositorio().criar(new Produto("MAIS UM PRODUTO", BigDecimal.valueOf(randomInt(1, 1000)), randomInt(200, 5000), null, 0L));
		getRepositorio().criar(new Produto("0xCAFEBABE *-*", BigDecimal.valueOf(randomInt(1, 1000)), randomInt(200, 5000), null, 0L));
		getRepositorio().criar(new Produto("JAVA VERMELHO ?", BigDecimal.valueOf(randomInt(1, 1000)), randomInt(200, 5000), null, 0L));
		
		getRepositorio().criar(new Produto("OK JÁ DEU PRODUTO 1", BigDecimal.valueOf(randomInt(1, 1000)), randomInt(200, 5000), null, 0L));
		getRepositorio().criar(new Produto("MEU DEUS PRODUTO 2", BigDecimal.valueOf(randomInt(1, 1000)), randomInt(200, 5000), LocalDateTime.now(), 0L));
		
	}

	public void registrarProduto(Produto produto) {
		
		validarNaoNulo(produto, "Produto");
		
		validarNaoVazio((isNullOrEmpty(produto.getNome())) , "Nome inválido");
		validar((produtoRepositorio.encontrarPorNome(produto.getNome())
				.isPresent()), "Nome já utilizado", "Nome diferente do já cadastrado");
		
		validar((produto.getQuantidadeEmEstoque() < 0), "Quantidade em Estoque inválido", "Valor maior que zero");
		validarNaoVazio((produto.getIdUnidade() == null || produto.getIdUnidade() < 0), "Unidade inválida");
		validar((produto.getPreco().doubleValue() < 0), "Preço inválido", "Valor maior que zero");
		
		getRepositorio().criar(produto);
		
	}

	public Optional<Produto> encontrarProdutoPorId(Long idProduto) {
		validarNaoNulo(idProduto, "Identificador do produto");
		return getRepositorio().encontrarPorId(idProduto);
	}
	
	public void deletarProduto(Produto produto) {
		validarNaoNulo(produto, "Produto");
		getRepositorio().deletar(produto);
	}
	
	
	public Collection<Produto> obterTodosProdutos() {
		return getRepositorio().todos();
	}
	
	public ProdutoRepositorio getRepositorio() {
		return produtoRepositorio;
	}

}
