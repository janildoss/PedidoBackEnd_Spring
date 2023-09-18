package com.api.PedidoBackEnd_Spring.domain;

import java.util.Date;

import com.api.PedidoBackEnd_Spring.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;

@Entity
public class PagamentoComBoleto extends Pagamento{
	private static final long serialVersionUID = 1L;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataVencimento;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataPagamento;
	
	public PagamentoComBoleto() {
		
	}

	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido ,Date dataVencimento, Date dataPagamento) {
		super(id, estado, pedido);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
		
	}
	

}
