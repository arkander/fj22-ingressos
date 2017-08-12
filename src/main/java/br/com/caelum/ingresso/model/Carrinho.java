package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.*;

import javax.enterprise.context.SessionScoped;

import org.springframework.stereotype.Component;

/**
 * Created by nando on 03/03/17.
 */


@Component
@SessionScoped
public class Carrinho {

    
    public List<Ingresso> getIngressos() {
		return ingressos;
	}

	public void setIngressos(List<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}

	private List<Ingresso> ingressos= new ArrayList<>();
    
    
    
    
    public void add(Ingresso ingresso){
    	ingressos.add(ingresso);
    }
    
    public boolean isSelecionado(Lugar lugar){
    	return ingressos.stream().map(Ingresso::getLugar).anyMatch(l -> l.equals(lugar) );
    }
    
    
    public BigDecimal getTotal(){
    	return ingressos.stream().map(Ingresso::getPreco).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    	
    }
    
    public Compra toCompra(){
    	return new Compra(ingressos);
    }
    
    public void emptyCart(){
    	this.ingressos.clear();
    }

	
}
