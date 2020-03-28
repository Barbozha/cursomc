package com.barboza.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barboza.cursomc.domain.Pedido;
import com.barboza.cursomc.repositories.PedidoRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class PedidoService {
	
	//chama uma operação lá na camada de acesso a dados
	//Na camada de acesso a dados (objeto do tipo pedido repository)
	//Declaro uma dependência do objeto do tipo pedidoRepository
	
	//Quando eu declaro uma dependência dentro de uma classe(no caso o repro) Esta dependência será
	//instanciada automaticamente pelo o SPRING, através do @Autowired
	@Autowired 
	private PedidoRepository repo; /* Abreviação de Repository */
	public Pedido buscar(Integer id) throws ObjectNotFoundException {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
}
