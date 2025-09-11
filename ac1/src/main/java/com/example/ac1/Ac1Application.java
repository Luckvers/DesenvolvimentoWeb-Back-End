package com.example.ac1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.ac1.Models.Aluno;
import com.example.ac1.Models.Curso;
import com.example.ac1.Repositories.AlunoRepository;
import com.example.ac1.Repositories.CursoRepository;

@SpringBootApplication
public class Ac1Application {
	@Bean
	public CommandLineRunner init(@Autowired CursoRepository cursoRepository,
			@Autowired AlunoRepository alunoRepository) {
		return args -> {
			Curso c1 = new Curso("Engenharia");
			Curso c2 = new Curso("Medicina");

			c1 = cursoRepository.inserir(c1);
			c2 = cursoRepository.inserir(c2);

			Aluno a1 = new Aluno("Luccas", 2024, c1);
			Aluno a2 = new Aluno("Caetano", 2020, c2);

			a1 = alunoRepository.inserir(a1);
			a2 = alunoRepository.inserir(a2);

			System.out.println("Cursos");
			for (Curso curso : cursoRepository.SelecionarTodos()) {
				System.out.println("Curso: " + curso.getNome() + ", ID " + curso.getId());
				System.out.println("--------------------");
			}
			System.out.println("Alunos");
			for (Aluno aluno : alunoRepository.selecionarTodos()) {
				System.out.println("Aluno: " + aluno.getNome() + ", ID " + aluno.getId());
				System.out.println("--------------------");
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(Ac1Application.class, args);
	}

}
