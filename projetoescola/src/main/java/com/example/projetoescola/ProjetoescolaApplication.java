package com.example.projetoescola;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.projetoescola.models.CategoriaCurso;
import com.example.projetoescola.models.Curso;
import com.example.projetoescola.models.Disciplina;
import com.example.projetoescola.repositories.CategoriaCursoRepository;
import com.example.projetoescola.repositories.CursoRepository;
import com.example.projetoescola.repositories.DisciplinaRepository;

@SpringBootApplication
public class ProjetoescolaApplication {
	@Bean
	public CommandLineRunner init(@Autowired CursoRepository cursoRepository,
			@Autowired CategoriaCursoRepository categoriaCursoRepository, @Autowired DisciplinaRepository disciplinaRepository) {
		return args -> {
			cursoRepository.salvar(
					new Curso(null, "teste", 2000));
			cursoRepository.salvar(
					new Curso(null, "teste2", 2050));
			CategoriaCurso c1 = categoriaCursoRepository.salvar(new CategoriaCurso(null, "Tecnólogo"));

			Disciplina d1 = disciplinaRepository.salvar(new Disciplina(null, "Banco de Dados"));
			

			List<Curso> listaCursos = cursoRepository.obterTodos();
			listaCursos.forEach(System.out::println);

			listaCursos.forEach(curso -> {
				curso.setCategoriaCurso(c1);
				curso.setDisciplinas(Arrays.asList(d1));
				cursoRepository.salvar(curso);
			});

			
		};
		
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjetoescolaApplication.class, args);
	}

}
