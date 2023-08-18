package com.api.PedidoBackEnd_Spring;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.api.PedidoBackEnd_Spring.domain.Categoria;
import com.api.PedidoBackEnd_Spring.domain.Cidade;
import com.api.PedidoBackEnd_Spring.domain.Cliente;
import com.api.PedidoBackEnd_Spring.domain.Endereco;
import com.api.PedidoBackEnd_Spring.domain.Estado;
import com.api.PedidoBackEnd_Spring.domain.Produto;
import com.api.PedidoBackEnd_Spring.enums.TipoCliente;
import com.api.PedidoBackEnd_Spring.repositories.CategoriaRepository;
import com.api.PedidoBackEnd_Spring.repositories.CidadeRepository;
import com.api.PedidoBackEnd_Spring.repositories.ClienteRepository;
import com.api.PedidoBackEnd_Spring.repositories.EnderecoRepository;
import com.api.PedidoBackEnd_Spring.repositories.EstadoRepository;
import com.api.PedidoBackEnd_Spring.repositories.ProdutoRepository;

@SpringBootApplication
public class PedidoBackEndSpringApplication implements CommandLineRunner {
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	

	public static void main(String[] args) {
		SpringApplication.run(PedidoBackEndSpringApplication.class, args);	
	}

	@Override
	public void run(String... args) throws Exception {
		// Adicionar dados as tabelas ctegoria e produtos
		Categoria cat1 = new Categoria(null,"Informatica");
		Categoria cat2 = new Categoria(null,"Escritorio");
		
		Produto p1 = new Produto(null,"Computador",2000.00);
		Produto p2 = new Produto(null,"Impressora",800.00);
		Produto p3 = new Produto(null,"Mouse",80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));		
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null,"São paulo");
		
		Cidade c1 = new Cidade(null,"Uberlandia",est1);
		Cidade c2 = new Cidade(null,"São Paulo",est2);
		Cidade c3 = new Cidade(null,"Campinas",est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null,"Maria Silva","maria@gmail.com","36378912377",TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("2735698","988745562"));
		
		Endereco e1 = new Endereco(null,"Rua Flores","300","Apto 303","JArdim Pampulha","38220834",cli1,c1);
		Endereco e2 = new Endereco(null,"Av.Matos","310","sala 453","Centro","3800245734",cli1,c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		
	}
	
	

}
