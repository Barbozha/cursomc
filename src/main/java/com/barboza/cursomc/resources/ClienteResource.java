package com.barboza.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.barboza.cursomc.domain.Cliente;
import com.barboza.cursomc.services.ClienteService;

import javassist.tools.rmi.ObjectNotFoundException;

// anotação 
// Vamos importar esta anotação digitando crtl + shift + o
//ou 
//botão window + preference
//localizar a palavra Keys
// Digitar no campo disponivel a palavra go to Sym
//selecionar Go Symbol in File 
//clicar no botão Unbind Command    para tirar o que estava escrito no campo Binding
//Clicar no botão Apply and Close
// depois deixar o cursor na frente da anotação @RequestController em seguida digitar as teclas crtl + shift + o


@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) throws ObjectNotFoundException {
	Cliente obj = service.buscar(id);
	return ResponseEntity.ok(obj);
		
	}
}
