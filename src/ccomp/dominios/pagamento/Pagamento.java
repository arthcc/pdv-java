package ccomp.dominios.pagamento;

import ccomp.core.base.NomeadaEntidadeBase;

public class Pagamento extends NomeadaEntidadeBase {

	public Pagamento(String nome) {
		setNome(nome);
	}
	
	public Pagamento() {}
	
	@Override
	public String toString() {
		return getNome();
	}

}
