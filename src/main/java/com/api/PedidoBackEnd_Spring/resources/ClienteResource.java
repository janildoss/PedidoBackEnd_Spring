package com.api.PedidoBackEnd_Spring.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

//CONTROLLER
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.PedidoBackEnd_Spring.DTO.ClienteDTO;
import com.api.PedidoBackEnd_Spring.DTO.ClienteNewDTO;
import com.api.PedidoBackEnd_Spring.domain.Cliente;
import com.api.PedidoBackEnd_Spring.services.ClienteService;

import jakarta.validation.Valid;

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
	
	@PutMapping(value="/{id}")
	//@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Cliente> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id){
		Cliente obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();		
	}
	
	
	//GET ALL
	@GetMapping("")
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> list = service.findAll();
		List<ClienteDTO> listDTO =list.stream().
				map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDTO);	
		
	}
	
	//Metodo para fazer paginacao
	@GetMapping(value="/page")
	public ResponseEntity<Page<ClienteDTO>> findPage(
		@RequestParam(value="page",defaultValue="0") Integer page,
		@RequestParam(value="linesPerPage",defaultValue="24") Integer linesPerPage,
		@RequestParam(value="orderBy",defaultValue="nome") String orderBy,
		@RequestParam(value="direction",defaultValue="ASC") String direction) {
				
		Page<Cliente> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listDTO =list.map(obj -> new ClienteDTO(obj));    
				
		return ResponseEntity.ok().body(listDTO);	
		
		//no postman		
		//http://localhost:8081/categorias/page?linesPerPage=3&page=1&direction=DESC
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto){
		Cliente obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	//POST
	//GET		
	//PUT
	//DELETE
		
	
}
