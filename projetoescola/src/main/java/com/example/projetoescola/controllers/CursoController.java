package com.example.projetoescola.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.projetoescola.dtos.CursoDTO;
import com.example.projetoescola.dtos.CursoRequestDTO;
import com.example.projetoescola.services.CursoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/curso")
public class CursoController {
    private CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping
    public void criarCurso(@RequestBody CursoRequestDTO cursoRequestDTO) {
        cursoService.salvar(cursoRequestDTO);
    }

    @GetMapping("{id}")
    public CursoDTO obterPorId(@PathVariable Long id) {
        return cursoService.obterPorId(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        cursoService.remover(id);
    }

    @PutMapping("{id}")
    public void editar(@PathVariable Long id, @RequestBody CursoRequestDTO dto) {
        cursoService.editar(id, dto);
    }

    @GetMapping()
    public List<CursoDTO> obterTodos() {
        return cursoService.obterTodos();
    }

}
