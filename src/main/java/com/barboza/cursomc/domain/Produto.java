package com.barboza.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

// A classe produto é uma entidade de dominio JPA 
// (escolhendo a INTERFACE do javax.persistence)

@Entity  // Fazendo o mapeamento básico com @Entity e o @Id
public class Produto implements Serializable{
	//Serializable (padrão: 1L)
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Double preco;
	
	
	// Esta anotação diz que do outro lado da associação já foram buscados os objetos e não precisa
	// buscar mais onde será omitido a lista de categorias para cada produto. 
	// Por isso eu uso o JsonBackReference
	@JsonBackReference
	
	// NO JPA, quando eu tenho no meu domínio muitos para muitos preciso configurar no meu
	// código também para que dê certo nas minhas tabelas, no banco de dados.
	// Eu codifico da seguinte maneira:
	
	//Faço a anotação
	@ManyToMany
	//eu defino quais são as tabelinhas no meu banco de dados para fazer o muitos para muitos
	@JoinTable(name = "PRODUTO_CATEGORIA",   // Defino o nome da tabela que ficará no meio das outras duas (PRODUTO_CATEGORIA)
			joinColumns = @JoinColumn(name = "produto_id"), //defino o campo da tabela correspondente ao código do produto, ou seja a chave estrangeira
			inverseJoinColumns = @JoinColumn(name = "categoria_id") //Aqui eu defino a outra chave estrangeira da tabale que irá corresponder a tabela categoria
			)  
	
	//No domínio tem que ser muitos para muitos
	private List<Categoria> categorias = new ArrayList<>();
	
	@OneToMany(mappedBy = "id.produto")
	//Digo que os produtos conhece os item associados a ele
	//para classe ItemPedidoPK
	private Set<ItemPedido> itens = new HashSet<>();
	
	//Construtores (não inclua coleções no construtor com parâmetros)
	public Produto() {
		
	}
	
	//Irei percorrer a lista de itens da minha classe
	public List<Pedido> getPedidos(){
		List<Pedido> lista = new ArrayList<>();
		for(ItemPedido x : itens) {
			lista.add(x.getPedido());
		}
		return lista;
	}

	public Produto(Integer id, String nome, Double preco) {
		super();
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

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}
	
	//hashCode e equals (implementação padrão: somente id)

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	
}
