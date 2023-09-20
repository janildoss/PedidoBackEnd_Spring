package com.api.PedidoBackEnd_Spring.resources;

//CONTROLLER
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.PedidoBackEnd_Spring.domain.Cliente;
import com.api.PedidoBackEnd_Spring.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	@Autowired
	private ClienteService service;
	
	//GET ONE
	//@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		Cliente obj = service.find(id);
		return ResponseEntity.ok().body(obj);		
	}
	
	//GET ALL
	//@GetMapping("/")
	/*public ResponseEntity<?> findAll(@PathVariable Integer id) {
		Cliente obj = (Cliente) service.buscarTodos();
		return ResponseEntity.ok().body(obj);		
	}*/
	
	//POST
	//GET		
	//PUT
	//DELETE
		
	
}
