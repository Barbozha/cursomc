package com.barboza.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.barboza.cursomc.domain.Categoria;
import com.barboza.cursomc.repositories.CategoriaRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	//Cria-se uma variável dependência para chamar o categoriaReposirory para
	//Gravar os dados na base de dados
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat01 = new Categoria(null,"Informática");
		Categoria cat02 = new Categoria(null,"Som");
		
		//Salvando na base de dados
		categoriaRepository.saveAll(Arrays.asList(cat01,cat02));
	}

}
