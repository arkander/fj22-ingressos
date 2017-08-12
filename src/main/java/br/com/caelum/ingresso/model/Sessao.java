package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Sessao {
	@Id @GeneratedValue
	private Integer id;
	
	private BigDecimal preco;
	
	@OneToMany
	private List<Ingresso> ingressos;
	
	public List<Ingresso> getIngressos() {
		return ingressos;
	}

	
	public boolean isDisponivel(Lugar lugar){
		return ingressos.stream().map(Ingresso::getLugar).noneMatch(l -> l.equals(lugar));
	}
	
	
	

	public void setIngressos(List<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}


	public BigDecimal getPreco() {
		return preco;
	}


	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

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
		this.preco = filme.getPreco().add(sala.getPreco());
	}

	public Filme getFilme() {
		return filme;
	}
	
	public Map<String,List<Lugar>> getMapaDeLugares(){
		return sala.getMapaDeLugares();
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
