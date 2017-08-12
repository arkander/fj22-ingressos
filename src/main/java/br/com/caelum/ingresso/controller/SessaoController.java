package br.com.caelum.ingresso.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.helpers.GerenciadorSessoes;
import br.com.caelum.helpers.TipoDeIngresso;
import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Carrinho;
import br.com.caelum.ingresso.model.ImagemCapa;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.form.SessaoForm;
import br.com.caelum.ingresso.rest.ImdbClient;

/**
 * Created by nando on 03/03/17.
 */
@Controller
public class SessaoController {

	@Autowired
	private SalaDao salaDao;
		
    @Autowired
    private FilmeDao filmeDao;
    
    @Autowired
    SessaoDao sessaoDao;
    
    @Autowired
    private ImdbClient client;
    
    @Autowired
    private Carrinho carrinho;
    


    @GetMapping({"/admin/sessao"})
    public ModelAndView form( SessaoForm form,@RequestParam("salaId")Integer salaId){
    	form.setSalaId(salaId);
        ModelAndView modelAndView = new ModelAndView("sessao/sessao");
        

        modelAndView.addObject("filmes", filmeDao.findAll());
        modelAndView.addObject("sala", salaDao.findOne(salaId));
        modelAndView.addObject("form", form);
        //modelAndView.addObject("form", form);
        

        return modelAndView;
    }


    @PostMapping("/admin/sessao")
    @Transactional
    public ModelAndView salva(@Valid SessaoForm form, BindingResult result){
    	if(result.hasErrors()){
    		return form(form,form.getSalaId());    		
    	}    	
    	Sessao s= form.toSessao(salaDao, filmeDao);  
    	
    	
    	GerenciadorSessoes gs= new  GerenciadorSessoes(sessaoDao.buscaSessoesDaSala(s.getSala()));
    	if(gs.cabe(s)){
    		sessaoDao.save(s);
            ModelAndView view = new ModelAndView("redirect:/admin/sala/"+form.getSalaId()+"/sessoes");       
            
            return view;
    	}
    	return form(form,s.getId());
    	
    	
    }
    
    @GetMapping("/sessao/{id}/lugares")    
    public ModelAndView lugaresNaSessao(@PathVariable("id") Integer sessaoId){
    	ModelAndView view = new ModelAndView("sessao/lugares");     
    	Sessao sessao = sessaoDao.findOne(sessaoId);
    	Optional<ImagemCapa> imagemCapa = client.request(sessao.getFilme(), ImagemCapa.class);
    	
    	
    	view.addObject("sessao", sessao);
    	view.addObject("imagemCapa", imagemCapa.orElse(new ImagemCapa()));
    	view.addObject("tiposDeIngressos", TipoDeIngresso.values());
    	view.addObject("carrinho", carrinho);
    	
    	return view;
    }
    
    
    
    
    
    
    
    
    
    @DeleteMapping("/admin/sessao/{id}")
    @ResponseBody
    @Transactional
    public void delete(@PathVariable("id") Integer id){
    	sessaoDao.delete(id);
    }
    

}
