package com.barboza.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barboza.cursomc.domain.Cliente;
import com.barboza.cursomc.repositories.ClienteRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ClienteService {
	
	//chama uma operação lá na camada de acesso a dados
	//Na camada de acesso a dados (objeto do tipo cliente repository)
	//Declaro uma dependência do objeto do tipo categoriaRepository
	
	//Quando eu declaro uma dependência dentro de uma classe(no caso o repro) Esta dependência será
	//instanciada automaticamente pelo o SPRING, através do @Autowired
	@Autowired 
	private ClienteRepository repo; /* Abreviação de Repository */
	public Cliente buscar(Integer id) throws ObjectNotFoundException {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
}
