package com.example.ac1.Repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.ac1.Models.Curso;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class CursoRepository {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Curso inserir(Curso curso) {
        curso = entityManager.merge(curso);
        return curso;
    }

    @Transactional
    public Curso editar(Curso curso) {
        return entityManager.merge(curso);
    }

    @Transactional
    public void excluir(Integer Id) {
        Curso curso = entityManager.find(Curso.class, Id);
        if (curso != null) {
            entityManager.remove(curso);
        }
    }

    @Transactional
    public List<Curso> SelecionarTodos() {
        String jpql = "SELECT c FROM Curso c";
        return entityManager.createQuery(jpql, Curso.class).getResultList();
    }

    @Transactional
    public Curso SelecionarporId(Integer Id) {
        return entityManager.find(Curso.class, Id);
    }
}
