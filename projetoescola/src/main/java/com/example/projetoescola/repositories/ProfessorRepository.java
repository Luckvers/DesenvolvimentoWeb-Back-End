package com.example.projetoescola.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projetoescola.models.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository <Professor, Long> {

    List<Professor> findBySalarioGreaterThanEqual (double salario);

    List<Professor> findBySalarioLessThanEqual (double salario);

    List<Professor> findByNomeLike (String nome);
    
} 
    

