package com.barboza.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.barboza.cursomc.domain.enums.TipoCliente;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Cliente  implements Serializable{
	//Serializable (padrão: 1L)
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String email;
	private String cpfoucnpj;
	private Integer tipo; // É um numero inteiro será exposto para o sistema o dado tipo cliente(Enum)
	
	@JsonManagedReference
	@OneToMany(mappedBy="cliente")
	private List<Endereco> enderecos = new ArrayList<>();
	
	
	
	@ElementCollection
	@CollectionTable(name="TELEFONE") //Para a tabela de telefone
	//O dominio Telefone é uma entidade fraca então irei implementar na própria classe Cliente
	//Através de uma colecao
	private Set<String> telefones = new HashSet<>();// É um conjunto de strings que não permite dados repetitivos
	
	public Cliente () {
		
	}

	
	//Construtor com argumentos ( source + Generator Construtor using field )
	public Cliente(Integer id, String nome, String email, String cpfoucnpj, TipoCliente tipo) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfoucnpj = cpfoucnpj;
		this.tipo = tipo.getCod();
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getCpfoucnpj() {
		return cpfoucnpj;
	}


	public void setCpfoucnpj(String cpfoucnpj) {
		this.cpfoucnpj = cpfoucnpj;
	}

	// é um macete para armazenar internamente um numero inteiro Controlado pelo Enum
	public TipoCliente getTipo() {
		return TipoCliente.toEnum(tipo);
	}


	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCod();
	}


	public List<Endereco> getEnderecos() {
		return enderecos;
	}


	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}


	public Set<String> getTelefones() {
		return telefones;
	}


	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
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
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
