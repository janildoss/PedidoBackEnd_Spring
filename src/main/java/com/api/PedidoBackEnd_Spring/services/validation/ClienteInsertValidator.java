package com.api.PedidoBackEnd_Spring.services.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.api.PedidoBackEnd_Spring.DTO.ClienteNewDTO;
import com.api.PedidoBackEnd_Spring.domain.Cliente;
import com.api.PedidoBackEnd_Spring.enums.TipoCliente;
import com.api.PedidoBackEnd_Spring.repositories.ClienteRepository;
import com.api.PedidoBackEnd_Spring.resources.exception.FieldMessage;
import com.api.PedidoBackEnd_Spring.services.validation.utils.BR;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	@Autowired
	private ClienteRepository repo;
	@Override
	public void initialize(ClienteInsert ann) {
		
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		// inclua os testes aqui, inserindo erros na lista
		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod())&& !BR.isValidCPF(objDto.getCpfOuCnpj())){
			list.add(new FieldMessage("CpfOuCnpj","Cpf Invalido"));
		}
		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod())&& !BR.isValidCNPJ(objDto.getCpfOuCnpj())){
			list.add(new FieldMessage("CpfOuCnpj","CNPJ Invalido"));
		}
		
		Cliente aux = repo.findByEmail(objDto.getEmail());
		if(aux !=null) {
			list.add(new FieldMessage("email","Email ja existente"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
