//package net.aibot.demo.controller.advice;
//
//import net.aibot.demo.controller.ResponseDto;
//import org.springframework.core.MethodParameter;
//import org.springframework.http.MediaType;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
//
//@RestControllerAdvice(basePackages = "net.aibot.demo.controller")
//public class SuccessHandler implements ResponseBodyAdvice<Object> {
//
//    @Override
//    public ResponseDto beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
//        return ResponseDto.builder()
//                .result(body)
//                .build();
//
//    }
//
//    @Override
//    public boolean supports(MethodParameter returnType, Class converterType) {
//        return true;
//    }
//}
