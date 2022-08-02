package net.aibot.demo.controller.advice;

import net.aibot.demo.exception.EmptyObjectException;
import net.aibot.demo.exception.ParentFileIsNotDirectoryException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;

@ControllerAdvice
public class CustomControllerExceptionHandler {

    @ExceptionHandler(EmptyObjectException.class)
    public ResponseEntity<HashMap<String, String>> EmptyObjectExceptionHandler(EmptyObjectException ex) {
        return new ResponseEntity<>(new HashMap<>(), ex.getStatus());
    }

    @ExceptionHandler(ParentFileIsNotDirectoryException.class)
    public ResponseEntity<HashMap<String, Object>> ParentFileIsNotDirectoryExceptionHandler(ParentFileIsNotDirectoryException ex, HttpServletRequest request) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("timestamp", new Date().getTime());
        hashMap.put("status", ex.getStatus().value());
        hashMap.put("error", ex.getClass().getSimpleName());
        hashMap.put("path", request.getServletPath());
        return new ResponseEntity<>(hashMap, ex.getStatus());
    }
}
