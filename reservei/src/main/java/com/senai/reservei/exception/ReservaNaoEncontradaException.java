package com.senai.reservei.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReservaNaoEncontradaException extends RuntimeException{
    public ReservaNaoEncontradaException() {
        super("Reserva não econtrada");
    }

    public ReservaNaoEncontradaException(String documento) {
        super("Nenhuma reserva encontrada para o documento " + documento);
    }
}
