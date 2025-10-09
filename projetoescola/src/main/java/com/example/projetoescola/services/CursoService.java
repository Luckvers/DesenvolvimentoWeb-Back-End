package com.example.projetoescola.services;

import java.util.List;

import com.example.projetoescola.dtos.CursoDTO;
import com.example.projetoescola.dtos.CursoRequestDTO;

public interface CursoService {
    void salvar(CursoRequestDTO cursoRequestDTO);
    void remover (Long id);
    void editar(Long id, CursoRequestDTO dto);
    CursoDTO obterPorId(Long id);
    List<CursoDTO> obterTodos();
    
}
