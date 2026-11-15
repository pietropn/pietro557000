package br.com.fiap.chuva.service;

public class RegistroNaoEncontradoException extends RuntimeException {

    public RegistroNaoEncontradoException(String message) {
        super(message);
    }
}
