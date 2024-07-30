package com.example.desafio_tabela_fipe.principal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.example.desafio_tabela_fipe.model.Dados;
import com.example.desafio_tabela_fipe.model.Modelos;
import com.example.desafio_tabela_fipe.model.Veiculo;
import com.example.desafio_tabela_fipe.service.ConsumoApi;
import com.example.desafio_tabela_fipe.service.ConverteDados;

public class Principal {

    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados converteDados = new ConverteDados();
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

        var marcas = converteDados.obterLista(json, Dados.class);
        marcas.stream().sorted(Comparator.comparing(Dados::codigo)).forEach(System.out::println);

        System.out.println("Informe o código da marca desejada:");
        var codigoMarca = scanner.nextLine();

        endereco = endereco + "/" + codigoMarca + "/modelos";
        json = consumoApi.obterDados(endereco);
        var modeloLista = converteDados.obterDados(json, Modelos.class);
        
        System.out.println("\nModelos dessa marca:\n");
        modeloLista.modelos().stream().sorted(Comparator.comparing(Dados::codigo))
                                        .forEach(System.out::println);

        System.out.println("Digite o nome do modelo desejado:");
        var nomeVeiculo = scanner.nextLine();

        List<Dados> modelosFiltrados = modeloLista.modelos().stream()
                .filter(m -> m.nome().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                .collect(Collectors.toList());

        System.out.println("\nModelos filtrados");
        modelosFiltrados.forEach(System.out::println);

        System.out.println("Digite por favor o código do modelo para buscar os valores de avaliação: ");
        var codigoModelo = scanner.nextLine();

        endereco = endereco + "/" + codigoModelo + "/anos";
        json = consumoApi.obterDados(endereco);
        List<Dados> anos = converteDados.obterLista(json, Dados.class);
        List<Veiculo> veiculos = new ArrayList<>();

        for (int i = 0; i < anos.size(); i++) {
            var enderecoAnos = endereco + "/" + anos.get(i).codigo();
            json = consumoApi.obterDados(enderecoAnos);
            Veiculo veiculo = converteDados.obterDados(json, Veiculo.class);
            veiculos.add(veiculo);
        }

        System.out.println("\nTodos os veículos filtrados com avaliações por ano: ");
        veiculos.forEach(System.out::println);

    }
}
