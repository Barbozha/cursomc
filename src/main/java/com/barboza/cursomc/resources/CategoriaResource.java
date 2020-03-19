package com.barboza.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.barboza.cursomc.domain.Categoria;

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
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Categoria> listar() {
		Categoria cat01 = new Categoria(01, "Informática");
		Categoria cat02 = new Categoria(02, "Som");
		
		List<Categoria> lista = new ArrayList<>();
		lista.add(cat01);
		lista.add(cat02);
		
			
		return lista;
	}
}
