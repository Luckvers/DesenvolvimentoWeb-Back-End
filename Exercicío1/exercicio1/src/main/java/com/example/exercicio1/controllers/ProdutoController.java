package com.example.exercicio1.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.exercicio1.models.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final List<Produto> produtos = new ArrayList<>();
    private final AtomicLong contadorId = new AtomicLong(1);

    @PostMapping
    public ResponseEntity<?> criarProduto(@RequestBody Produto produto) {

        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("O nome do produto não pode estar vazio");
        }

        if (produto.getPreco() == null || produto.getPreco() <= 0) {
            return ResponseEntity.badRequest().body("O preço deve ser um valor positivo");
        }

        produto.setId(contadorId.getAndIncrement());
        produtos.add(produto);

        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        return ResponseEntity.ok(produtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarProduto(@PathVariable Long id, @RequestBody Produto produtoAtualizado) {

        if (produtoAtualizado.getNome() == null || produtoAtualizado.getNome().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("O nome do produto não pode estar vazio");
        }

        if (produtoAtualizado.getPreco() == null || produtoAtualizado.getPreco() <= 0) {
            return ResponseEntity.badRequest().body("O preço deve ser um valor positivo");
        }

        Optional<Produto> produtoExistente = produtos.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst();

            if (produtoExistente.isPresent()) {
                Produto produto = produtoExistente.get();
                produto.setNome(produtoAtualizado.getNome());
                produto.setPreco(produtoAtualizado.getPreco());
                return ResponseEntity.ok(produto);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado com ID: " + id);
            }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirProduto(@PathVariable Long id) {
        Optional<Produto> produtoParaExcluir = produtos.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst();

            if (produtoParaExcluir.isPresent()) {
                produtos.remove(produtoParaExcluir.get());
                return ResponseEntity.ok("Produto excluído com sucesso");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado com ID: " + id);
            }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarProdutoPorId(@PathVariable Long id) {
        Optional<Produto> produto = produtos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    
        if (produto.isPresent()) {
            return ResponseEntity.ok(produto.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado com ID: " + id);
        }
    }
    }
    
