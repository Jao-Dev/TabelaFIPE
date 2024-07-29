package com.example.desafio_tabela_fipe.principal;

import java.util.Scanner;

import com.example.desafio_tabela_fipe.service.ConsumoApi;

public class Principal {

    private ConsumoApi consumoApi = new ConsumoApi();
    private Scanner scanner = new Scanner(System.in);
    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

    public void exibeMenu(){    
        
        var menu = "Digite a opção desejada (Carro, moto ou caminhão): ";
        
        System.out.println(menu);
        var opcao = scanner.nextLine();
        String endereco;

        if (opcao.toLowerCase().contains("carr")) {
            endereco = URL_BASE + "carros/marcas";
        }else if (opcao.toLowerCase().contains("mot")){
            endereco = URL_BASE + "motos/marcas";
        }else{
            endereco = URL_BASE + "caminhoes/marcas";
        }

        var json = consumoApi.obterDados(endereco);
        System.out.println(json);
    }
}
