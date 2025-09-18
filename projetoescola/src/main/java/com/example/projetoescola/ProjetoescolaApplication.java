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
import com.example.projetoescola.models.Professor;
import com.example.projetoescola.repositories.CategoriaCursoRepository;
import com.example.projetoescola.repositories.CursoRepository;
import com.example.projetoescola.repositories.DisciplinaRepository;
import com.example.projetoescola.repositories.ProfessorRepository;

@SpringBootApplication
public class ProjetoescolaApplication {
	@Bean
	public CommandLineRunner init(@Autowired CursoRepository cursoRepository,
			@Autowired CategoriaCursoRepository categoriaCursoRepository, @Autowired DisciplinaRepository disciplinaRepository, @Autowired ProfessorRepository professorRepository) {
		return args -> {
			cursoRepository.save(
					new Curso(null, "Análise de Sistemas", 2000, null, null));
			cursoRepository.save(
					new Curso(null, "Jogos Digitais", 2050, null, null));
			CategoriaCurso c1 = categoriaCursoRepository.save(new CategoriaCurso(null, "Tecnólogo",null));

			Disciplina d1 = disciplinaRepository.salvar(new Disciplina(null, "Banco de Dados"));
			
			


			List<Curso> listaCursos = cursoRepository.findAll();
			listaCursos.forEach(System.out::println);

			listaCursos.forEach(curso -> {
				curso.setCategoriaCurso(c1);
				curso.setDisciplinas(Arrays.asList(d1));
				cursoRepository.save(curso);
			});

			System.out.println("Cursos que começam com 'Jogos'");
			cursoRepository.findByNomeLike("Jogos%").forEach(System.out::println);

			professorRepository.save(new Professor(null, "Cleber", 3000));
			professorRepository.save(new Professor(null, "Vitório", 10000));
			professorRepository.save(new Professor(null, "Ronaldo", 1000));
			professorRepository.save(new Professor(null, "Frey", 7000));
			professorRepository.save(new Professor(null, "Anderson", 2000));
			professorRepository.save(new Professor(null, "Welington", 3500));

			System.out.println("Professores com salário maior ou igual a 2000");
			professorRepository.findBySalarioGreaterThanEqual(2000).forEach(System.out::println);

			System.out.println("Professores com salário menor ou igual a 2000");
			professorRepository.findBySalarioLessThanEqual(2000).forEach(System.out::println);

			System.out.println("Professores que o nome contém 'y");
			professorRepository.findByNomeLike("%y%").forEach(System.out::println);
			
		};
		
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjetoescolaApplication.class, args);
	}

}
