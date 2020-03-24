package com.barboza.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.barboza.cursomc.domain.Categoria;
import com.barboza.cursomc.domain.Cidade;
import com.barboza.cursomc.domain.Estado;
import com.barboza.cursomc.domain.Produto;
import com.barboza.cursomc.repositories.CategoriaRepository;
import com.barboza.cursomc.repositories.CidadeRepository;
import com.barboza.cursomc.repositories.EstadoRepository;
import com.barboza.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	//Cria-se uma variável dependência para chamar o categoriaReposirory para
	//Gravar os dados na base de dados
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
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
		
		
		//Incluindo dados para Estado e Cidade
		//Realizando as instancias das cidades para Estados
		Estado est01 = new Estado(null, "SP");
		Estado est02 = new Estado(null, "MG");
		Estado est03 = new Estado(null, "RJ");
		
		Cidade cid01 = new Cidade(null,"São Paulo", est01);
		Cidade cid02 = new Cidade(null, "Extrema", est02);
		Cidade cid03 = new Cidade(null, "Campinas", est01);
		Cidade cid04 = new Cidade(null, "Rio de Janeiro", est03);
		
		//Instanciando Estado para cidade
		est01.getCidade().addAll(Arrays.asList(cid01,cid03));
		est02.getCidade().addAll(Arrays.asList(cid02));
		est03.getCidade().addAll(Arrays.asList(cid04));
		
		//Salvando as categorias de Cidade no Ropository
		//Na regra de negócio (dominio) deve-se salvar primeiro o estado
		//para depois salvar as cidades dentro do estado
		estadoRepository.saveAll(Arrays.asList(est01,est02,est03));
		
		//Salvando as cidades dentro do estado
		cidadeRepository.saveAll(Arrays.asList(cid01,cid02,cid03,cid04));
		
		
		
	}

}
