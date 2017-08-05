package br.com.caelum.ingresso.model;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Sessao {
	@Id @GeneratedValue
	private Integer id;
	
	@ManyToOne
	private Filme filme;
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	private LocalTime horario;
	
	@ManyToOne
	private Sala sala;
	
	public Sessao() {
		super();
		
	}
	

	public Sessao(Filme filme, LocalTime horario, Sala sala) {
		super();
		this.filme = filme;
		this.horario = horario;
		this.sala = sala;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}
	
	public LocalTime getHorarioTermino(){
		
		return this.horario.plus(filme.getDuracao().toMinutes(),ChronoUnit.MINUTES);
	}
	
	
	
	

}
