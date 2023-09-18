package com.api.PedidoBackEnd_Spring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.PedidoBackEnd_Spring.domain.Pedido;
import com.api.PedidoBackEnd_Spring.repositories.PedidoRepository;
import com.api.PedidoBackEnd_Spring.services.exception.ObjectNotFoundException;


@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repo;	
	
	//Tratamento para quando pesquisar categoria nao existente POR ID
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}	
		
}
