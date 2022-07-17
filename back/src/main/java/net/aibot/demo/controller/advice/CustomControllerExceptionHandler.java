package net.aibot.demo.controller.advice;

import net.aibot.demo.service.EmptyObjectException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class CustomControllerExceptionHandler {

    @ExceptionHandler(EmptyObjectException.class)
    public ResponseEntity<HashMap<String, String>> EmptyObjectExceptionHandler(EmptyObjectException ex) {
        return new ResponseEntity<>(new HashMap<>(), ex.getStatus());
    }
}
