package br.com.fiap.chuva.service;

/** Lancada quando o registro procurado nao existe mais no banco. */
public class RegistroNaoEncontradoException extends RuntimeException {

    public RegistroNaoEncontradoException(String message) {
        super(message);
    }
}
