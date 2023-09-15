package com.api.PedidoBackEnd_Spring.domain;

import com.api.PedidoBackEnd_Spring.enums.EstadoPagamento;

import jakarta.persistence.Entity;

@Entity
public class PagamentoComCartao extends Pagamento{
	private static final long serialVersionUID = 1L;
 private Integer numeroDeParcelas;
 
 public PagamentoComCartao() {	 
 }

public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
	super(id, estado, pedido);
	this.numeroDeParcelas = numeroDeParcelas;
	
}
 
 
}

	