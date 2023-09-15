package com.api.PedidoBackEnd_Spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import com.api.PedidoBackEnd_Spring.domain.Pedido;

//@Repository
public interface PedidoRepository extends JpaRepository <Pedido,Integer> {
	
}
