package br.com.caelum.ingresso.model;

import java.math.BigDecimal;

public class DescontoTrintaPorCento implements Desconto{

	private BigDecimal percentual = new BigDecimal("0.3");
	
	@Override
	public BigDecimal aplicarDescontoSobre(BigDecimal precoOriginal) {
		return precoOriginal.subtract(trintaPorCento(precoOriginal));
	}
	
	private BigDecimal trintaPorCento(BigDecimal precoOriginal){
		return precoOriginal.multiply(percentual);
	}
	@Override
	public String getDescricao() {
		return "Desconto Banco";
	}
}
