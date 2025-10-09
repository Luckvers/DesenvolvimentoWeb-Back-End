package com.example.projetoescola.configs;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.projetoescola.dtos.APIErrorDTO;
import com.example.projetoescola.dtos.RegraNegocioException;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ApplicationControllerAdvice {
    @ExceptionHandler (RegraNegocioException.class)
    public APIErrorDTO handleRegraNegocioException (RegraNegocioException ex){
        String msg = ex.getMessage();
        return new APIErrorDTO(msg);
    }
}
