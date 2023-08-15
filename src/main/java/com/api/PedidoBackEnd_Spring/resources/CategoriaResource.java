package com.api.PedidoBackEnd_Spring.resources;

//CONTROLLER
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.PedidoBackEnd_Spring.domain.Categoria;
import com.api.PedidoBackEnd_Spring.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	@Autowired
	private CategoriaService service;
	
	//GET ONE
	//@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@GetMapping("/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Categoria obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);		
	}
	
	//GET ALL
	//@GetMapping("/")
	/*public ResponseEntity<?> findAll(@PathVariable Integer id) {
		Categoria obj = (Categoria) service.buscarTodos();
		return ResponseEntity.ok().body(obj);		
	}*/
	
	//POST
	//GET		
	//PUT
	//DELETE
		
	
}
