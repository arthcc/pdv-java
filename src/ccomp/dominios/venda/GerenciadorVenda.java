package ccomp.dominios.venda;

import java.util.Collection;

public class GerenciadorVenda {
	
	private VendaRepositorio vendaRepositorio;
	
	public GerenciadorVenda() {
		vendaRepositorio = new VendaRepositorio();
	}

	public void inserirVendaAoSistema(Venda venda) {
		vendaRepositorio.criar(venda);
	}
	
	public Venda encontrarVendaPorId(Long idVenda) {
		return vendaRepositorio.encontrarPorId(idVenda)
				.orElse(null);
	}
	
	public Collection<Venda> obterTodasAsVendas() {
		return vendaRepositorio.todos();
	}
	
	public VendaRepositorio getVendaRepositorio() {
		return vendaRepositorio;
	}

}
