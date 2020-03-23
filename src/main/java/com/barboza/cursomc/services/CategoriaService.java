package com.barboza.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barboza.cursomc.domain.Categoria;
import com.barboza.cursomc.repositories.CategoriaRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	//chama uma operação lá na camada de acesso a dados
	//Na camada de acesso a dados (objeto do tipo categoria repository)
	//Declaro uma dependência do objeto do tipo categoriaRepository
	
	//Quando eu declaro uma dependência dentro de uma classe(no caso o repro) Esta dependência será
	//instanciada automaticamente pelo o SPRING, através do @Autowired
	@Autowired 
	private CategoriaRepository repo; /* Abreviação de Repository */
	public Categoria buscar(Integer id) throws ObjectNotFoundException {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
}
