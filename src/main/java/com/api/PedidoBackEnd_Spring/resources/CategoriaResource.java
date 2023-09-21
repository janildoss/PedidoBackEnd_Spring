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

import com.api.PedidoBackEnd_Spring.DTO.CategoriaDTO;
import com.api.PedidoBackEnd_Spring.domain.Categoria;
import com.api.PedidoBackEnd_Spring.services.CategoriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	@Autowired
	private CategoriaService service;
	
	//GET ONE
	//@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@GetMapping("/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Categoria obj = service.find(id);
		return ResponseEntity.ok().body(obj);		
	}
	
	/*@PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Categoria obj){
        obj = service.insert(obj);
        return ResponseEntity.created(null).body(null); 
    }*/
	
	@PostMapping
	public ResponseEntity<Categoria> insert(@Valid @RequestBody CategoriaDTO objDto){
		Categoria obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value="/{id}")
	//@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Categoria> update(@Valid @RequestBody CategoriaDTO objDto, @PathVariable Integer id){
		Categoria obj = service.fromDTO(objDto);
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
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<Categoria> list = service.findAll();
		List<CategoriaDTO> listDTO =list.stream().
				map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDTO);	
		
	}
	
	//Metodo para fazer paginacao
	@GetMapping(value="/page")
	public ResponseEntity<Page<CategoriaDTO>> findPage(
		@RequestParam(value="page",defaultValue="0") Integer page,
		@RequestParam(value="linesPerPage",defaultValue="24") Integer linesPerPage,
		@RequestParam(value="orderBy",defaultValue="nome") String orderBy,
		@RequestParam(value="direction",defaultValue="ASC") String direction) {
				
		Page<Categoria> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> listDTO =list.map(obj -> new CategoriaDTO(obj));    
				
		return ResponseEntity.ok().body(listDTO);	
		
		//no postman		
		//http://localhost:8081/categorias/page?linesPerPage=3&page=1&direction=DESC
		
	}
	
	
	
	//POST
	//GET		
	//PUT
	//DELETE
		
	
}
