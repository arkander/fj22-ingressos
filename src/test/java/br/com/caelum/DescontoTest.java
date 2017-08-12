package br.com.caelum;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.helpers.TipoDeIngresso;
import br.com.caelum.ingresso.model.DescontoEstudante;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.SemDesconto;
import br.com.caelum.ingresso.model.Sessao;

public class DescontoTest {

	@Test
	public void deveConcederDescontoTrintaPorCento() {
		
		Sala sala = new Sala("Sala 1",new BigDecimal("20.5"));
		Lugar lugar= new Lugar("A",1);
		Filme filme = new Filme("Transformers",Duration.ofMinutes(120),"Sci-fi",new BigDecimal("12.0"));
		BigDecimal precoEsperado = new BigDecimal("22.75");
		
		
		Sessao sessao = new Sessao(filme,LocalTime.now(),sala);
		
		Ingresso ingresso = new Ingresso(sessao,TipoDeIngresso.BANCO,lugar);
		
		
		Assert.assertEquals(precoEsperado,ingresso.getPreco());
	}
	
	@Test
	public void deveConcederDesconto50PorCento() {
		Lugar lugar= new Lugar("A",1);
		Sala sala = new Sala("Sala 1",new BigDecimal("20.5"));
		Filme filme = new Filme("Transformers",Duration.ofMinutes(120),"Sci-fi",new BigDecimal("12.0"));
		BigDecimal precoEsperado = new BigDecimal("16.25");
		
		
		Sessao sessao = new Sessao(filme,LocalTime.now(),sala);
		
		Ingresso ingresso = new Ingresso(sessao,TipoDeIngresso.ESTUDANTE,lugar);
		
		
		Assert.assertEquals(precoEsperado,ingresso.getPreco());
	}
	
	@Test
	public void naoDeveConcederDescontoParaIngressoNormal() {
		Lugar lugar= new Lugar("A",1);
		Sala sala = new Sala("Sala 1",new BigDecimal("20.5"));
		Filme filme = new Filme("Transformers",Duration.ofMinutes(120),"Sci-fi",new BigDecimal("12.0"));
		BigDecimal precoEsperado = new BigDecimal("32.5");
		
		
		Sessao sessao = new Sessao(filme,LocalTime.now(),sala);
		
		Ingresso ingresso = new Ingresso(sessao,TipoDeIngresso.INTEIRO,lugar);
		
		
		Assert.assertEquals(precoEsperado,ingresso.getPreco());
	}

}
