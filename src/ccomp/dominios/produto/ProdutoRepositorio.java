package ccomp.dominios.produto;

import java.util.Optional;

import ccomp.core.impl.EmMemoriaRepositorio;

public class ProdutoRepositorio extends EmMemoriaRepositorio<Produto> {
	
	
	public Optional<Produto> encontrarPorNome(String nome) {
		return todos()
				.stream()
				.filter(produto -> produto.getNome()
						.equalsIgnoreCase(nome))
				.findFirst();
	}
	
}
