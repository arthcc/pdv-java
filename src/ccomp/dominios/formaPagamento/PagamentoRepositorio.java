package ccomp.dominios.formaPagamento;


import java.util.Optional;

import ccomp.core.impl.EmMemoriaRepositorio;

public class PagamentoRepositorio extends EmMemoriaRepositorio<TipoPagamento> {
	
	public Optional<TipoPagamento> encontrarPorNome(String nome) {
		return todos()
				.stream()
				.filter(pagamento-> pagamento.getNome()
						.equalsIgnoreCase(nome))
				.findFirst();
	}
	
}
