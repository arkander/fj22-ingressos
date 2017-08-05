package br.com.caelum;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.helpers.GerenciadorSessoes;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorSessaoTestTest {

	@Test
	public void verificaPodeCadastrarDuasSessoesSemHorariosConflitantes() {
		
		Sala sala = new Sala("Sala 1", BigDecimal.valueOf(10));
		Filme filme = new Filme("Transformers",Duration.ofMinutes(120),"Sci-fi1",BigDecimal.ONE);
		Sessao sessao = new Sessao(filme,LocalTime.parse("12:45"),sala);
		List<Sessao> sessoes = Arrays.asList(new Sessao(filme,LocalTime.parse("18:45"),sala));
		
		GerenciadorSessoes gs= new  GerenciadorSessoes(sessoes);
		Assert.assertTrue(gs.cabe(sessao));
	}
	
	@Test
	public void garanteQueNaoDevePermitirSessoesTerminandoDentroDoHorarioDeUmaSessaoExistente() {
		
		Sala sala = new Sala("Sala 1",BigDecimal.valueOf(10));
		Filme filme = new Filme("Transformers",Duration.ofMinutes(120),"Sci-fi1",BigDecimal.ONE);
		LocalTime lt = LocalTime.parse("10:00");
		List<Sessao> sessoes = Arrays.asList(new Sessao(filme,lt,sala));
		Sessao sessao = new Sessao(filme,lt.minusHours(1),sala);
		
		
		GerenciadorSessoes gs= new  GerenciadorSessoes(sessoes);
		Assert.assertFalse(gs.cabe(sessao));
	}
	
	
	@Test
	public void garanteQueNaoDevePermitirSessoesIniciandoDentroDoHorarioDeUmaSessaoExistente() {
		
		Sala sala = new Sala("Sala 1",BigDecimal.ONE);
		Filme filme = new Filme("Transformers",Duration.ofMinutes(120),"Sci-fi1",BigDecimal.ONE);
		LocalTime lt = LocalTime.parse("10:00");
		List<Sessao> sessoes = Arrays.asList(new Sessao(filme,lt,sala));
		Sessao sessao = new Sessao(filme,lt.plusHours(1),sala);
		
		
		GerenciadorSessoes gs= new  GerenciadorSessoes(sessoes);
		Assert.assertFalse(gs.cabe(sessao));
	}
	
	@Test
	public void goPrecoDaSessaoDeveSerIgualASomaDoPrecoDaSalaMaisOPrecoDoFilme() {
		
		Sala sala = new Sala("Sala 1",new BigDecimal("22.5"));
		Filme filme = new Filme("Transformers",Duration.ofMinutes(120),"Sci-fi",new BigDecimal("12.0"));
		LocalTime lt = LocalTime.parse("10:00");
		List<Sessao> sessoes = Arrays.asList(new Sessao(filme,lt,sala));
		
		BigDecimal somaPreco = sala.getPreco().add(filme.getPreco());
		
		
		Sessao sessao = new Sessao(filme,LocalTime.now(),sala);
		
		Assert.assertEquals(somaPreco,sessao.getPreco());
	}
	
	
	@Test
	public void garanteQueDevePermitirUmaInsercaoEntreDoisFilmes() {
		
		Sala sala = new Sala("",BigDecimal.valueOf(10));
		Filme filme1 = new Filme("Transformers",Duration.ofMinutes(120),"Sci-fi1",BigDecimal.ONE);
		LocalTime dezHoras = LocalTime.parse("10:00");
		Sessao sessaoDasDez = new Sessao(filme1,dezHoras,sala);
				
		Filme filme2 = new Filme("Transformers",Duration.ofMinutes(120),"Sci-fi1",BigDecimal.ONE);
		LocalTime dezoitoHoras = LocalTime.parse("18:00");
		Sessao sessaoDasDezoito = new Sessao(filme2,dezHoras,sala);
		
		
		
		List<Sessao> sessoes = Arrays.asList(sessaoDasDez,sessaoDasDezoito);
		
		
		
		GerenciadorSessoes gs= new  GerenciadorSessoes(sessoes);
		Assert.assertTrue(gs.cabe(new Sessao(filme2,LocalTime.parse("13:00"),sala)));
	}
	
	
	

}
