package br.com.caelum.ingresso.controller;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.form.SessaoForm;

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
    


    @GetMapping({"/admin/sessao"})
    public ModelAndView form( Integer salaId,SessaoForm form){

        ModelAndView modelAndView = new ModelAndView("sessao/sessao");


        modelAndView.addObject("filmes", filmeDao.findAll());
        modelAndView.addObject("sala", salaDao.findOne(salaId));
        

        return modelAndView;
    }


    @PostMapping("/admin/sessao")
    @Transactional
    public ModelAndView salva(@Valid SessaoForm form, BindingResult result){
    	if(result.hasErrors()){
    		return form(form.getSalaId(),form);
    		
    	}
    	
    	Sessao s= form.toSessao(salaDao, filmeDao);
       
    	sessaoDao.save(s);

        ModelAndView view = new ModelAndView("redirect:/admin/sala/"+form.getSalaId()+"/sessoes");

        return view;
    }
    
    
    

}
