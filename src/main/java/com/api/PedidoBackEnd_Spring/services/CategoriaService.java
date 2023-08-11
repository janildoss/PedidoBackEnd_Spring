package com.api.PedidoBackEnd_Spring.services;

//import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.GetMapping;

import com.api.PedidoBackEnd_Spring.domain.Categoria;
import com.api.PedidoBackEnd_Spring.repositories.CategoriaRepository;


@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);		
	}
	//@GetMapping
	//public Categoria buscarTodos(Integer id) {
	//	Optional<Categoria> obj = repo.findAll(Categoria);
	//	return obj.orElse(null);		
	//}
	
	//@GetMapping
	//public List<Categoria>buscarTodos(){
	//	return repo.findAll();
	//}
	

}
