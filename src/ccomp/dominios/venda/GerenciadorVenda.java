package ccomp.dominios.venda;

public class GerenciadorVenda {
	
	private VendaRepositorio vendaRepositorio;
	
	public GerenciadorVenda() {
		vendaRepositorio = new VendaRepositorio();
	}
	
	public VendaRepositorio getVendaRepositorio() {
		return vendaRepositorio;
	}

}
