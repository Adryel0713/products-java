package com.adryel0713.backend.repositories;

import com.adryel0713.backend.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {

    Optional<Produto> findByNome(String nome);

}
