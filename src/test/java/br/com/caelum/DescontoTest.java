package br.com.caelum;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.ingresso.model.DescontoEstudante;
import br.com.caelum.ingresso.model.DescontoTrintaPorCento;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.SemDesconto;
import br.com.caelum.ingresso.model.Sessao;

public class DescontoTest {

	@Test
	public void deveConcederDescontoTrintaPorCento() {
		
		Sala sala = new Sala("Sala 1",new BigDecimal("20.5"));
		Filme filme = new Filme("Transformers",Duration.ofMinutes(120),"Sci-fi",new BigDecimal("12.0"));
		BigDecimal precoEsperado = new BigDecimal("22.75");
		
		
		Sessao sessao = new Sessao(filme,LocalTime.now(),sala);
		
		Ingresso ingresso = new Ingresso(sessao,new DescontoTrintaPorCento());
		
		
		Assert.assertEquals(precoEsperado,ingresso.getPreco());
	}
	
	@Test
	public void deveConcederDesconto50PorCento() {
		
		Sala sala = new Sala("Sala 1",new BigDecimal("20.5"));
		Filme filme = new Filme("Transformers",Duration.ofMinutes(120),"Sci-fi",new BigDecimal("12.0"));
		BigDecimal precoEsperado = new BigDecimal("16.25");
		
		
		Sessao sessao = new Sessao(filme,LocalTime.now(),sala);
		
		Ingresso ingresso = new Ingresso(sessao,new DescontoEstudante());
		
		
		Assert.assertEquals(precoEsperado,ingresso.getPreco());
	}
	
	@Test
	public void naoDeveConcederDescontoParaIngressoNormal() {
		
		Sala sala = new Sala("Sala 1",new BigDecimal("20.5"));
		Filme filme = new Filme("Transformers",Duration.ofMinutes(120),"Sci-fi",new BigDecimal("12.0"));
		BigDecimal precoEsperado = new BigDecimal("32.5");
		
		
		Sessao sessao = new Sessao(filme,LocalTime.now(),sala);
		
		Ingresso ingresso = new Ingresso(sessao,new SemDesconto());
		
		
		Assert.assertEquals(precoEsperado,ingresso.getPreco());
	}

}
