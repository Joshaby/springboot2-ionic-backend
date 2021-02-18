package com.nelioalves.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*; //Libs do JPA
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity // Usado para informar que a classe irá virar um entidade em um tabela com mesmo nome da classe
public class Produto implements Serializable {
    private static final long serialVersionUID = 1L; // indentificador de serialização e desserialização usado para verificar compatibilidade e versionamento entre objeto serializado e uma classe desse objeto que implementa a interface Serializable

    @Id // Usado para atribuir um ID a entidadade, que será um chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // método de geração do ID que será baseado no autoincremento do BD
    private Integer id;
    private String nome;
    private Double preco;
    @ManyToMany // definição de que a classe Produto tem relacionamento muitos para muitos com Categoria, e isso em conceitos de BD, é necessário a criação de uma tabela! Relacionamento é feito encima do atribúto categoriaList
    @JoinTable(name = "PRODUTO_CATEGORIA", //nome de tabela
            joinColumns = @JoinColumn(name = "produto_id"), //nome da coluna que vai conter os IDs do produtos
            inverseJoinColumns = @JoinColumn(name = "categoria_ID") // nome da coluna que vai conter os IDs das categorias
    )
    private List<Categoria> categoriasList = new ArrayList<>(); // Atribúto de objeto que irá conter Categoria de um produto
    @OneToMany(mappedBy = "id.produto") // método que indica que outra classe, ItemPedido, já fez o mapeamento, o relacionamento um para muitos. Mapeamento foi feito usando atribúto Produto do objeto ID de ItemPedido
    private Set<ItemPedido> itemPedidoSet = new HashSet<>();

    public Produto() {

    }

    public Produto(Integer id, String nome, Double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
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
    public Double getPreco() {
        return preco;
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }
    public List<Categoria> getCategoriaList() {
        return categoriasList;
    }
    @JsonIgnore
    public void setCategoriaList(List<Categoria> categoriaLst) {
        this.categoriasList = categoriaLst;
    }
    @JsonIgnore
    public Set<ItemPedido> getItemPedidoSet() {
        return itemPedidoSet;
    }
    public void setItemPedidoSet(Set<ItemPedido> itemPedidoSet) {
        this.itemPedidoSet = itemPedidoSet;
    }
    @JsonIgnore
    public List<Pedido> getPedidos() {
        return itemPedidoSet.stream().map(ItemPedido::getPedido).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return id.equals(produto.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
