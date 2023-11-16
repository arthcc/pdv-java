package ccomp.dominios.formaPagamento;


import java.util.Optional;

import ccomp.core.impl.EmMemoriaRepositorio;
import ccomp.dominios.pagamento.Pagamento;

public class PagamentoRepositorio extends EmMemoriaRepositorio<Pagamento> {
	
	public Optional<Pagamento> encontrarPorNome(String nome) {
		return todos()
				.stream()
				.filter(pagamento-> pagamento.getNome()
						.equalsIgnoreCase(nome))
				.findFirst();
	}
	
}
