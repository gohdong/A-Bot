package net.aibot.demo.config;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CORSFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        res.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        res.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "*");
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {

    }
}
