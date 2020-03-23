package com.barboza.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.barboza.cursomc.domain.Categoria;
import com.barboza.cursomc.domain.Produto;
import com.barboza.cursomc.repositories.CategoriaRepository;
import com.barboza.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	//Cria-se uma variável dependência para chamar o categoriaReposirory para
	//Gravar os dados na base de dados
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//Vamos instanciar a classe Categoria conforme o nosso domínio
		Categoria cat01 = new Categoria(null,"Informática");
		Categoria cat02 = new Categoria(null,"Som");
		
		//Vamos instanciar a classe produto conforme o nosso domínio
		Produto prod01 = new Produto(null, "PC", 2500.00);
		Produto prod02 = new Produto(null, "Fone de Ouvido", 90.00);
		Produto prod03 = new Produto(null, "NoteBook", 3000.00);
		
		//Relacionando as categorias aos produtos
		cat01.getProdutos().addAll(Arrays.asList(prod01,prod03));
		cat02.getProdutos().addAll(Arrays.asList(prod02));
		
		
		//Relacionando os produtos as categorias.
		prod01.getCategorias().addAll(Arrays.asList(cat01));
		prod02.getCategorias().addAll(Arrays.asList(cat02));
		prod03.getCategorias().addAll(Arrays.asList(cat01));
		
		
		//Salvando as categorias no repository
		categoriaRepository.saveAll(Arrays.asList(cat01, cat02));
		
		//Salvando os produtos no repository
		produtoRepository.saveAll(Arrays.asList(prod01, prod02, prod03));
	}

}
