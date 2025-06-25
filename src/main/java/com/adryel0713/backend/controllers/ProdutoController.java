package com.adryel0713.backend.controllers;

import com.adryel0713.backend.model.Membro;
import com.adryel0713.backend.model.Produto;
import com.adryel0713.backend.services.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<Produto>> buscarProdutos(){
        List<Produto> produtos = produtoService.buscarProdutos().getBody();
        return ResponseEntity.ok(produtos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Long id){
        return produtoService.buscarProdutoPorId(id);
    }
    @GetMapping("/buscar")
    public ResponseEntity<Produto> buscarProdutoPorNome(@RequestParam String nome){
        return produtoService.buscarProdutoPorNome(nome);
    }
    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto){
        return produtoService.criarProduto(produto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id,@RequestBody Produto produto){
        return produtoService.atualizarProduto(id,produto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarProduto(@PathVariable Long id){
        return produtoService.deletarProduto(id);
    }

}
