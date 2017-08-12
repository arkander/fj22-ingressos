package br.com.caelum.helpers;

import java.math.BigDecimal;

import br.com.caelum.ingresso.model.Desconto;
import br.com.caelum.ingresso.model.DescontoEstudante;
import br.com.caelum.ingresso.model.DescontoTrintaPorCento;
import br.com.caelum.ingresso.model.SemDesconto;

public enum TipoDeIngresso {
	
	INTEIRO(new SemDesconto()),
	ESTUDANTE(new DescontoEstudante()),
	BANCO(new DescontoTrintaPorCento());
	
	private final Desconto desconto;
	
	TipoDeIngresso(Desconto desconto){
		this.desconto = desconto;
	}
	
	public BigDecimal aplicaDesconto(BigDecimal valor){
		return desconto.aplicarDescontoSobre(valor);
		
	}
	
	public String getDescricao(){
		return desconto.getDescricao();
		
	}
	

}
