package com.example.desafio_tabela_fipe.service;

public interface IConvertDados {
    <T> T obterDados(String json, Class<T> classe);
}
