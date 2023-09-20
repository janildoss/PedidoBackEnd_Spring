package com.api.PedidoBackEnd_Spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.api.PedidoBackEnd_Spring.domain.Categoria;
import com.api.PedidoBackEnd_Spring.repositories.CategoriaRepository;
import com.api.PedidoBackEnd_Spring.services.exception.DataIntegrityException;
import com.api.PedidoBackEnd_Spring.services.exception.ObjectNotFoundException;


@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repo;	
	
	//Tratamento para quando pesquisar categoria nao existente POR ID
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}	
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj); 
	    //return new ResponseEntity<>(repo.save(obj),HttpStatus.CREATED);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos! ");
		}
		
	}
	
	public List<Categoria> findAll(){
		return repo.findAll();
	}
	
		
}
