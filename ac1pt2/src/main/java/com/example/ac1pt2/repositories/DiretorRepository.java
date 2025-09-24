package com.example.ac1pt2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ac1pt2.models.Diretor;


@Repository
public interface DiretorRepository extends JpaRepository<Diretor, Long> {
    List<Diretor> findByNomeStartingWith (String nome);
    
    @Query("SELECT d FROM Diretor d LEFT JOIN FETCH d.filmes WHERE d.id = :id")
    Diretor findByIdWithFilmes(Long id);
}
