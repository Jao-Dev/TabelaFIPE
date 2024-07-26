package com.example.desafio_tabela_fipe.principal;

import java.util.Scanner;

import com.example.desafio_tabela_fipe.service.ConsumoApi;
import com.example.desafio_tabela_fipe.service.ConverteDados;

public class Principal {
    
    private Scanner scanner = new Scanner(System.in);

    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados converteDados = new ConverteDados();

    private final String ENDERECO1 = "https://parallelum.com.br/fipe/api/v1/";
    private final String ENDERECO2 = "/marcas";

    public void exibeMenu(){
        System.out.println("Digite o tipo de veículo desejado (carro, caminhão ou moto):");
        var tipoVeiculo = scanner.nextLine();
        var json = consumoApi.obterDados(ENDERECO1 + tipoVeiculo + ENDERECO2);
    }

}
