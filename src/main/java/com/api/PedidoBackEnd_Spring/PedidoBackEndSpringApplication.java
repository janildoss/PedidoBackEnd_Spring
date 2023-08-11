package com.api.PedidoBackEnd_Spring;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.api.PedidoBackEnd_Spring.domain.Categoria;
import com.api.PedidoBackEnd_Spring.repositories.CategoriaRepository;

@SpringBootApplication
public class PedidoBackEndSpringApplication implements CommandLineRunner {
	@Autowired
	private CategoriaRepository categoriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(PedidoBackEndSpringApplication.class, args);	
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Categoria cat1 = new Categoria(null,"Informatica");
		Categoria cat2 = new Categoria(null,"Escritorio");
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
	}
	
	

}
