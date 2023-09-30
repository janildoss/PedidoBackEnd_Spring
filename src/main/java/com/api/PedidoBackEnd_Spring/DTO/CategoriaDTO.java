package com.api.PedidoBackEnd_Spring.DTO;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.api.PedidoBackEnd_Spring.domain.Categoria;

import jakarta.validation.constraints.NotBlank;

public class CategoriaDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotBlank(message="Preenchimento obrigatorio")
	@Length(min=5,max=80,message="O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;
	
	public CategoriaDTO() {
		
	}
	
	public CategoriaDTO(Categoria obj) {
		id = obj.getId();
		nome = obj.getNome();
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
