package ccomp.dominios.produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import ccomp.core.base.EntidadeBase;

public class Produto extends EntidadeBase {

	private String nome;
	private BigDecimal preco;
	private int quantidadeEmEstoque;
	
	private Long idUnidade;
	private LocalDateTime dataUltimaVenda;

	public Produto(String nome, BigDecimal preco, 
			       int quantidadeEmEstoque, LocalDateTime dataUltimaVenda,
			       Long idUnidade) 
	{
		this.nome = nome;
		this.preco = preco;
		this.quantidadeEmEstoque = quantidadeEmEstoque;
		this.dataUltimaVenda = dataUltimaVenda;
		this.idUnidade = idUnidade;
	}
	
	public Produto() {}

	public String getNome() {
		return nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public int getQuantidadeEmEstoque() {
		return quantidadeEmEstoque;
	}

	public LocalDateTime getDataUltimaVenda() {
		return dataUltimaVenda;
	}

	public Long getIdUnidade() {
		return idUnidade;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
		this.quantidadeEmEstoque = quantidadeEmEstoque;
	}

	public void setIdUnidade(Long idUnidade) {
		this.idUnidade = idUnidade;
	}

	public void setDataUltimaVenda(LocalDateTime dataUltimaVenda) {
		this.dataUltimaVenda = dataUltimaVenda;
	}

	@Override
	public String toString() {
		return "Produto [id=" + getId() + ", nome=" + nome + ", preco=" + preco + ", quantidadeEmEstoque=" + quantidadeEmEstoque
				+ ", idUnidade=" + idUnidade + ", dataUltimaVenda=" + dataUltimaVenda + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Produto)
			return false;

		Produto outro = (Produto) obj;
		return getNome().equals(outro.getNome());

	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getNome());
	}
	
}
