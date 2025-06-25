package com.adryel0713.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
    import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandleException {

    @ExceptionHandler(NaoEncontradoException.class)
    public ResponseEntity<?> naoEncontrado(NaoEncontradoException naoEncontradoException){
        Map<String,Object> response = new LinkedHashMap<>();
        response.put("timestamp", System.currentTimeMillis());
        response.put("status", HttpStatus.NOT_FOUND.value());
        response.put("error","Recurso não encontrado");
        response.put("message",naoEncontradoException.getMessage());
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalException(Exception exception){
        Map<String,Object> response = new LinkedHashMap<>();
        response.put("timestamp", System.currentTimeMillis());
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("error","Erro interno do servidor");
        response.put("message",exception.getMessage());
        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
