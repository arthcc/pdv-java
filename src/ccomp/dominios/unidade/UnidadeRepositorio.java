package ccomp.dominios.unidade;


import java.util.Optional;

import ccomp.core.impl.EmMemoriaRepositorio;

public class UnidadeRepositorio extends EmMemoriaRepositorio<Unidade> {
	
	public Optional<Unidade> encontrarPorNome(String nome) {
		return todos()
				.stream()
				.filter(unidade -> unidade.getNome()
						.equalsIgnoreCase(nome))
				.findFirst();
	}
	
}
