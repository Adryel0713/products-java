package com.adryel0713.backend.services;

import com.adryel0713.backend.model.Produto;
import com.adryel0713.backend.repositories.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }


    public ResponseEntity<Produto> criarProduto(Produto produto){
        return ResponseEntity.ok(repository.save(produto));
    }
    public ResponseEntity<Produto> buscarProdutoPorId(Long id){
        Optional<Produto> Produto = repository.findById(id);
        if(!Produto.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Produto.get());
    }
    public ResponseEntity<Produto> buscarProdutoPorNome(String nome){
        Optional<Produto> Produto = repository.findByNome(nome);
        if(!Produto.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Produto.get());
    }
    public ResponseEntity<List<Produto>> buscarProdutos(){
        return ResponseEntity.ok(repository.findAll());
    }
    public ResponseEntity<Produto> atualizarProduto(Long id,Produto produto){
        Optional<Produto> produtoOptional = repository.findById(id);
        if(!produtoOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Produto p = produtoOptional.get();

        p.setNome(produto.getNome());
        p.setDescricao(produto.getDescricao());
        p.setPreco(produto.getPreco());
        p.setQuantidade(produto.getQuantidade());
        p.setCategoria(produto.getCategoria());
        p.setImagem(produto.getImagem());

        return ResponseEntity.ok(repository.save(p));
    }
    public ResponseEntity<?> deletarProduto(Long id){
        Optional<Produto> Produto = repository.findById(id);
        if(!Produto.isPresent()){
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
