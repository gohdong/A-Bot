package net.aibot.demo.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;

@ControllerAdvice
public class CustomControllerExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<HashMap<String, Object>> EmptyObjectExceptionHandler(IllegalArgumentException ex, HttpServletRequest request) {
        return new ResponseEntity<>(makeExceptionMap(ex, request), HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(ParentFileIsNotDirectoryException.class)
//    public ResponseEntity<HashMap<String, Object>> ParentFileIsNotDirectoryExceptionHandler(ParentFileIsNotDirectoryException ex, HttpServletRequest request) {
//        HashMap<String, Object> hashMap = makeExceptionMap(ex, request);
//        return new ResponseEntity<>(hashMap, ex.getStatus());
//    }

    private HashMap<String, Object> makeExceptionMap(Exception ex, HttpServletRequest request) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("timestamp", new Date().getTime());
        hashMap.put("error", ex.getMessage());
        hashMap.put("path", request.getServletPath());
        return hashMap;
    }
}
