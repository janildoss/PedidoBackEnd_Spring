package com.api.PedidoBackEnd_Spring.resources;

//CONTROLLER
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.PedidoBackEnd_Spring.domain.Pedido;
import com.api.PedidoBackEnd_Spring.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {
	@Autowired
	private PedidoService service;
	
	//GET ONE
	//@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> find(@PathVariable Integer id) {
		Pedido obj = service.find(id);
		return ResponseEntity.ok().body(obj);		
	}
	
	//GET ALL
	//@GetMapping("/")
	/*public ResponseEntity<?> findAll(@PathVariable Integer id) {
		Pedido obj = (Pedido) service.buscarTodos();
		return ResponseEntity.ok().body(obj);		
	}*/
	
	//POST
	//GET		
	//DELETE
		
	
}
