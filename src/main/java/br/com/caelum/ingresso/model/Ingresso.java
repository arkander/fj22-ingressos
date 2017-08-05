package br.com.caelum.ingresso.model;

import java.math.BigDecimal;

public class Ingresso {
	
	private Sessao sessao;
	private BigDecimal preco;
	
	public Sessao getSessao() {
		return sessao;
	}
	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	public Ingresso(Sessao sessao, Desconto desconto) {
		super();
		this.sessao = sessao;
		this.preco = desconto.aplicarDescontoSobre(sessao.getPreco());
	}

}
