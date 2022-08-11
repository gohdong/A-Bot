package net.aibot.demo.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Slf4j
@ControllerAdvice
public class CustomControllerExceptionHandler {
    //TODO aop error log

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<LinkedHashMap<String, Object>> IllegalArgumentExceptionHandler(IllegalArgumentException ex, HttpServletRequest request) {
        log.error(ex.getMessage(), ex);
        return makeResponseEntity(ex, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<LinkedHashMap<String, Object>> ExceptionHandler(MethodArgumentNotValidException ex, HttpServletRequest request) {
        log.error(ex.getMessage(), ex);
        return makeResponseEntity("Check your input value", request, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(ParentFileIsNotDirectoryException.class)
//    public ResponseEntity<HashMap<String, Object>> ParentFileIsNotDirectoryExceptionHandler(ParentFileIsNotDirectoryException ex, HttpServletRequest request) {
//        HashMap<String, Object> hashMap = makeExceptionMap(ex, request);
//        return new ResponseEntity<>(hashMap, ex.getStatus());
//    }

    private ResponseEntity<LinkedHashMap<String, Object>> makeResponseEntity(Exception ex, HttpServletRequest request, HttpStatus httpStatus) {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<>();
        hashMap.put("timestamp", new Date().getTime());
        hashMap.put("status", httpStatus.value());
        hashMap.put("error", ex.getMessage());
        hashMap.put("path", request.getServletPath());

        return new ResponseEntity<>(hashMap, httpStatus);
    }

    private ResponseEntity<LinkedHashMap<String, Object>> makeResponseEntity(String errorMessage, HttpServletRequest request, HttpStatus httpStatus) {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<>();
        hashMap.put("timestamp", new Date().getTime());
        hashMap.put("status", httpStatus.value());
        hashMap.put("error", errorMessage);
        hashMap.put("path", request.getServletPath());

        return new ResponseEntity<>(hashMap, httpStatus);
    }
}
