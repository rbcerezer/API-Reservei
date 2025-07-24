package com.senai.reservei.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ValidacaoException extends RuntimeException{
    public ValidacaoException(String message) {
        super(message);
    }
}
