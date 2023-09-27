package com.api.PedidoBackEnd_Spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.PedidoBackEnd_Spring.DTO.ClienteDTO;
import com.api.PedidoBackEnd_Spring.DTO.ClienteNewDTO;
import com.api.PedidoBackEnd_Spring.domain.Cidade;
import com.api.PedidoBackEnd_Spring.domain.Cliente;
import com.api.PedidoBackEnd_Spring.domain.Endereco;
import com.api.PedidoBackEnd_Spring.enums.TipoCliente;
import com.api.PedidoBackEnd_Spring.repositories.ClienteRepository;
import com.api.PedidoBackEnd_Spring.repositories.EnderecoRepository;
import com.api.PedidoBackEnd_Spring.services.exception.DataIntegrityException;
import com.api.PedidoBackEnd_Spring.services.exception.ObjectNotFoundException;


@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repo;	
	//@Autowired
	//private CidadeRepository cidadeRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	//Tratamento para quando pesquisar Cliente nao existente POR ID
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}	
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj; 	 
		
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj,obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possivel excluir uma categoria porque há entidades relacionadas! ");
		}
		
	}
	
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy); 
		
		return repo.findAll(pageRequest);
	}
	
	//Metodo auxiliar
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(),objDto.getEmail(),null,null);
		//return new Cliente(objDto.getId(),objDto.getNome());
	}
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente  cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
		Cidade cid = new  Cidade(objDto.getCidadeId(),null,null);
		
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(),
				objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);	
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if(objDto.getTelefone2()!=null) {
			cli.getTelefones().add(objDto.getTelefone2());			
		}
		if(objDto.getTelefone3()!=null) {
			cli.getTelefones().add(objDto.getTelefone3());			
		}
		return cli;
	}
	
	
	private void updateData(Cliente newObj,Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
			
}
