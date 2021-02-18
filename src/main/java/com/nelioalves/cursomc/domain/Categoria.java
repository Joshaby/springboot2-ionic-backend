package com.nelioalves.cursomc.domain;

import javax.persistence.*; // Libs do JPA!
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity //Usado para informar que a classe irá virar um entidade de um tabela de mesmo nome da classe
public class Categoria implements Serializable {
    private static final long serialVersionUID = 1L; // indentificador usado para serializar ou desserializar um objeto de uma classe que implementa a interface Serializable. Usado para verificar compatibilidade entre uma classe e um objeto serializado

    @Id // Usado para atruibuir um ID a entidade, que vai ser um chave primária
    @GeneratedValue(strategy=GenerationType.IDENTITY) // método de geração do ID, será usado autoincremento para geração dos números
    private Integer id;
    private String nome;
    @ManyToMany(mappedBy = "categoriasList") // método usado para fazer relacionamento muitos para muitos, que já foi mapeado por outra classe, que foi a Produto usando seu atribúto categoriaList
    private List<Produto> produtosList = new ArrayList<>();

    public Categoria(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Categoria() {

    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public List<Produto> getProdutosList() {
        return produtosList;
    }
    public void setProdutosList(List<Produto> produtoList) {
        this.produtosList = produtoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categoria)) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(id, categoria.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
