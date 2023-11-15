package ccomp.dominios.unidade;

import ccomp.core.base.NomeadaEntidadeBase;

public class Unidade extends NomeadaEntidadeBase {

	public Unidade(String nome) {
		setNome(nome);
	}
	
	public Unidade() {}
	
	@Override
	public String toString() {
		return getNome();
	}

}
