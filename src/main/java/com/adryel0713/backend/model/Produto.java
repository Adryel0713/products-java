package com.adryel0713.backend.model;

import jakarta.persistence.*;

import javax.swing.text.DateFormatter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private BigDecimal preco;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private String imagem;

    @Column(nullable = false)
    private LocalDateTime dataCadastro;

    private LocalDateTime dataAtualizacao;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        dataCadastro = now;
        dataAtualizacao = now;
    }

    @PreUpdate
    protected void onUpdate() {
        dataAtualizacao = LocalDateTime.now();
    }

    public Produto() {
    }

    public Produto(String nome, String descricao, BigDecimal preco, Integer quantidade, String categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.categoria = categoria;
    }

    public Produto(String nome, String descricao, BigDecimal preco, Integer quantidade, String categoria, String imagem) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.imagem = imagem;
    }

    public Long getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

}
