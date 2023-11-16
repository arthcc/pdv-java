package ccomp.dominios.venda;

import java.math.BigDecimal;

import ccomp.core.base.EntidadeBase;

public class ItemVenda extends EntidadeBase {

	private long idProduto;
	private BigDecimal valorUnitario; 
	private BigDecimal valorDesconto;
	private BigDecimal valorAcrescimo;
	private double quantidadeTotal;

	
	public ItemVenda(long idProduto, BigDecimal valorUnitario, BigDecimal valorDesconto, 
			BigDecimal valorAcrescimo, double quantidadeTotal) 
	{
		this.idProduto = idProduto;
		this.valorUnitario = valorUnitario;
		this.valorDesconto = valorDesconto;
		this.valorAcrescimo = valorAcrescimo;
		this.quantidadeTotal = quantidadeTotal;
	}
	
	public ItemVenda() {}
	
	public long getIdProduto() {
		return idProduto;
	}
	
	public void setIdProduto(long idProduto) {
		this.idProduto = idProduto;
	}
	
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}
	
	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	
	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}
	
	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}
	
	public BigDecimal getValorAcrescimo() {
		return valorAcrescimo;
	}
	
	public void setValorAcrescimo(BigDecimal valorAcrescimo) {
		this.valorAcrescimo = valorAcrescimo;
	}
	
	public double getQuantidadeTotal() {
		return quantidadeTotal;
	}
	
	public void setQuantidadeTotal(double quantidadeTotal) {
		this.quantidadeTotal = quantidadeTotal;
	}
	
	/* valorUnitario * quantidadeTotal - valorDesconto + valorAcrescimo */
	public BigDecimal getValorTotalItem() {
		return valorUnitario
				.multiply(BigDecimal.valueOf(quantidadeTotal))
				.subtract(valorDesconto)
				.add(valorAcrescimo);
	}
	
	
}
