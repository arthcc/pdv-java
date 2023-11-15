package ccomp.dominios.venda;

import java.math.BigDecimal;

import ccomp.core.base.EntidadeBase;

public class ItemVenda extends EntidadeBase {

	private long idProduto;
	private BigDecimal precoItem;
	private int quantidadeItem;
	private BigDecimal valorTotalItem; 
	
	
	public ItemVenda(long idProduto, BigDecimal precoItem, int quantidadeItem, BigDecimal valorTotalItem) {
		this.idProduto = idProduto;
		this.precoItem = precoItem;
		this.quantidadeItem = quantidadeItem;
		this.valorTotalItem = valorTotalItem;
	}


	public long getIdProduto() {
		return idProduto;
	}


	public void setIdProduto(long idProduto) {
		this.idProduto = idProduto;
	}


	public BigDecimal getPrecoItem() {
		return precoItem;
	}


	public void setPrecoItem(BigDecimal precoItem) {
		this.precoItem = precoItem;
	}


	public int getQuantidadeItem() {
		return quantidadeItem;
	}


	public void setQuantidadeItem(int quantidadeItem) {
		this.quantidadeItem = quantidadeItem;
	}


	public BigDecimal getValorTotalItem() {
		return valorTotalItem;
	}


	public void setValorTotalItem(BigDecimal valorTotalItem) {
		this.valorTotalItem = valorTotalItem;
	}

	
	
}
