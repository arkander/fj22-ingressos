package br.com.caelum.helpers;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorSessoes {
	
	private List<Sessao> sessoes;
	
		
	public GerenciadorSessoes(List<Sessao> sessoesSala){
		this.sessoes = sessoesSala;
		
	}
	
	
	public boolean cabe(Sessao atual){
		
		Optional<Boolean> opc =  sessoes.stream().map(existente -> isSessaoValida(atual, existente)).reduce(Boolean::logicalAnd);
		return opc.orElse(true);
		
	}
	
	
	private boolean isSessaoValida(Sessao atual, Sessao existente){
		LocalTime he= existente.getHorario();
		LocalTime ha= atual.getHorario();
		boolean ehAntes = ha.isBefore(he);
		if(ehAntes){
			return atual.getHorarioTermino().isBefore(he);
			
		}
		
		return existente.getHorarioTermino().isBefore(ha);
	}
	
	
	
	

}
