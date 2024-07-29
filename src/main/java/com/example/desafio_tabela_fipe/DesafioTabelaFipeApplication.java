package com.example.desafio_tabela_fipe;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.desafio_tabela_fipe.principal.Principal;

@SpringBootApplication
public class DesafioTabelaFipeApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(DesafioTabelaFipeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		Principal principal = new Principal();
		principal.exibeMenu();
	}

}
