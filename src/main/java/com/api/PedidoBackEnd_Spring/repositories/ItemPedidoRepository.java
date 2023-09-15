package com.api.PedidoBackEnd_Spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import com.api.PedidoBackEnd_Spring.domain.ItemPedido;

//@Repository
public interface ItemPedidoRepository extends JpaRepository <ItemPedido,Integer> {
	
}
