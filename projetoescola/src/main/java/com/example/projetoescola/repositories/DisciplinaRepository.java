package com.example.projetoescola.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.example.projetoescola.models.Disciplina;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class DisciplinaRepository {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Disciplina salvar(Disciplina disciplina){
        disciplina = entityManager.merge(disciplina);
        return disciplina;
    }
    public List<Disciplina> listarTodos(){
        String jpql = "SELECT c FROM CategoriaCurso c";
        return entityManager.createQuery(jpql, Disciplina.class).getResultList();
    }
}
