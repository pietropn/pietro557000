package br.com.fiap.chuva.controller;

import br.com.fiap.chuva.service.RegistroNaoEncontradoException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.support.RequestContextUtils;

@ControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(RegistroNaoEncontradoException.class)
    public String registroNaoEncontrado(RegistroNaoEncontradoException e, HttpServletRequest request) {
        RequestContextUtils.getOutputFlashMap(request).put("erro", e.getMessage());
        return "redirect:/";
    }
}
