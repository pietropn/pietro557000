package br.com.fiap.chuva.service;

/** Lancada quando uma regra de negocio impede a operacao (R2 e R3). */
public class RegraNegocioException extends RuntimeException {

    public RegraNegocioException(String message) {
        super(message);
    }
}
