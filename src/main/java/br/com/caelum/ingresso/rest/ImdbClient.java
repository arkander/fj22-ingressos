package br.com.caelum.ingresso.rest;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.caelum.ingresso.model.DetalhesFilme;
import br.com.caelum.ingresso.model.Filme;

@Component
public class ImdbClient {
	
	private Logger logger = Logger.getLogger(ImdbClient.class);
	
	
	
	public Optional<DetalhesFilme> requestOld(Filme filme){
		RestTemplate client  = new RestTemplate();
		
		String titulo = filme.getNome().replace(" ", "+");
		String url = String.format("https://imdb-fj22.herokuapp.com/imdb?title=%s", titulo);
		try{
			DetalhesFilme detalhes = client.getForObject(url, DetalhesFilme.class);
			return Optional.of(detalhes);
			
		}catch(RestClientException re){
			logger.error(re.getMessage(),re);
			return Optional.empty();
		}
		
		
	}
	
	
	public <T> Optional<T> request(Filme filme, Class<T> tClass){
		RestTemplate client  = new RestTemplate();
		
		String titulo = filme.getNome().replace(" ", "+");
		String url = String.format("https://imdb-fj22.herokuapp.com/imdb?title=%s", titulo);
		try{
			return Optional.of(client.getForObject(url, tClass));
			
		}catch(RestClientException re){
			logger.error(re.getMessage(),re);
			return Optional.empty();
		}
		
	}
	
	
	

}
