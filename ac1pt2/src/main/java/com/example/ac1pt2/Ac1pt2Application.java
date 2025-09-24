package com.example.ac1pt2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.ac1pt2.models.Diretor;
import com.example.ac1pt2.models.Filme;
import com.example.ac1pt2.repositories.DiretorRepository;
import com.example.ac1pt2.repositories.FilmeRepository;

@SpringBootApplication
public class Ac1pt2Application {

    @Bean
    public CommandLineRunner init(@Autowired FilmeRepository filmeRepository, DiretorRepository diretorRepository) {
        return args -> {
            Diretor d1 = new Diretor(null, "Quentin Tarantino", null);
            Diretor d2 = new Diretor(null, "Tim Burton", null);

            d1 = diretorRepository.save(d1);
            d2 = diretorRepository.save(d2);

            filmeRepository.save(new Filme(null, "Guardiões das Galáxias", 200, d1));
            filmeRepository.save(new Filme(null, "Os Incríveis", 150, d2));
            filmeRepository.save(new Filme(null, "Harry Potter", 100, d2));

            System.out.println("Filmes com a duração maior que 100 minutos: ");
            filmeRepository.findByDuracaoGreaterThan(100).forEach(System.out::println);

            System.out.println("Filmes com a duração menor ou igual a 200 minutos: ");
            filmeRepository.findByDuracaoLessThanEqual(200).forEach(System.out::println);

            System.out.println("Filmes que contém 'Harry' no Título: ");
            filmeRepository.findByTituloLike("%Harry%").forEach(System.out::println);

            System.out.println("Diretores que o nome começa com 'Q': ");
            diretorRepository.findByNomeStartingWith("Q").forEach(System.out::println);

            System.out.println("Diretor com Id '2' ");
            diretorRepository.findByIdWithFilmes(2L).getFilmes().forEach(System.out::println);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Ac1pt2Application.class, args);
    }

}
