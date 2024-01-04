package com.generation.crudfarmacia.controller;

import java.util.List;
import java.util.Optional;

//ProdutoController.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.crudfarmacia.model.Produto;
import com.generation.crudfarmacia.repository.CategoriaRepository;
import com.generation.crudfarmacia.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

 @Autowired
 private ProdutoRepository produtoRepository;

 @SuppressWarnings("unused") 
@Autowired
 private CategoriaRepository categoriaRepository;

 @GetMapping
 public List<Produto> listarProdutos() {
     return produtoRepository.findAll();
 }

 @GetMapping("/{id}")
 public ResponseEntity<Produto> obterProdutoPorId(@PathVariable Long id) {
     Optional<Produto> produto = produtoRepository.findById(id);
     return produto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
 }

 @PostMapping
 public ResponseEntity<Produto> criarProduto(@RequestBody Produto novoProduto) {
     Produto produtoSalvo = produtoRepository.save(novoProduto);
     return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
 }

 @PutMapping("/{id}")
 public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody Produto produtoAtualizado) {
     if (!produtoRepository.existsById(id)) {
         return ResponseEntity.notFound().build();
     }
     produtoAtualizado.setId(id);
     Produto produtoSalvo = produtoRepository.save(produtoAtualizado);
     return ResponseEntity.ok(produtoSalvo);
 }

 @DeleteMapping("/{id}")
 public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
     if (!produtoRepository.existsById(id)) {
         return ResponseEntity.notFound().build();
     }
     produtoRepository.deleteById(id);
     return ResponseEntity.noContent().build();
 }
}
