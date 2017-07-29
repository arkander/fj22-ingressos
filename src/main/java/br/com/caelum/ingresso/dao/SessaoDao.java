package br.com.caelum.ingresso.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

/**
 * Created by nando on 03/03/17.
 */
@Repository
public class SessaoDao {

    @PersistenceContext
    private EntityManager manager;


    public Sessao findOne(Integer id) {
        return manager.find(Sessao.class, id);
    }

    public void save(Sessao sessao) {
        manager.persist(sessao);
    }

    public List<Sessao> findSalaById(Sala sala) {
        return manager.createQuery("select s from Sessao s where s.Sala = :sala", Sessao.class).setParameter("sala", sala).getResultList();
    }
    
    public List<Sessao> findAll() {
        return manager.createQuery("select f from Filme f", Sessao.class).getResultList();
    }

    public void delete(Integer id) {
        manager.remove(findOne(id));
    }
}
