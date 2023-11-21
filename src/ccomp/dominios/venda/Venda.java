package ccomp.dominios.venda;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import ccomp.core.base.EntidadeBase;
import ccomp.dominios.formaPagamento.TipoPagamento;

public class Venda extends EntidadeBase {

	private List<ItemVenda> itens;
	private LocalDateTime dataTempoVenda;
	private TipoPagamento pagamento;
	
	public Venda(List<ItemVenda> itens, LocalDateTime dataTempoVenda, TipoPagamento pagamento) {
		this.itens = itens;
		this.dataTempoVenda = dataTempoVenda;
		this.pagamento = pagamento;
	}
	
	public TipoPagamento getPagamento() {
		return pagamento;
	}
	
	public void setPagamento(TipoPagamento pagamento) {
		this.pagamento = pagamento;
	}

	public List<ItemVenda> getItens() {
		return itens;
	}

	public void setItens(List<ItemVenda> itens) {
		this.itens = itens;
	}

	public LocalDateTime getDataTempoVenda() {
		return dataTempoVenda;
	}

	public void setDataTempoVenda(LocalDateTime dataTempoVenda) {
		this.dataTempoVenda = dataTempoVenda;
	}

	public BigDecimal getValorTotal() {
		return itens.stream()
				.map(item -> item.getValorTotalItem())
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
}
