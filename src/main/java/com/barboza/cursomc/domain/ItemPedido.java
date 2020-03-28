package com.barboza.cursomc.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPedido implements Serializable{
	//Serializable (padrão: 1L)
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore //Ignoro esta chave dupla para a serialização
	@EmbeddedId //é um id imbutino no auxiliar
	private ItemPedidoPK id = new ItemPedidoPK();//O id é um atributo composto, como se fosse um atributo primitivo
	//Quando vc faz uma entidade JPA tendo como atributo a classe ItemPedidoPK temos que implementar a classe ItemPedidoPK
	
	private Double desconto;
	private Integer quantidade;
	private Double preco;
	
	public ItemPedido() {
		
	}

	public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
		super();
		id.setPedido(pedido);
		id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}
	
	//Temos que ignorar a referência cíclica antes de usar o getPedido
	@JsonIgnore
	//Para eu ter acesso ao produto fora da minha classe itemPedido
	public Pedido getPedido() {
		return id.getPedido();
	}
	
	//Temos que ignorar tambem a referência cíclica antes de usar o getProduto
	//@JsonIgnore
	public Produto getProduto() {
		return id.getProduto();
	}

	public ItemPedidoPK getId() {
		return id;
	}

	public void setId(ItemPedidoPK id) {
		this.id = id;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

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
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
