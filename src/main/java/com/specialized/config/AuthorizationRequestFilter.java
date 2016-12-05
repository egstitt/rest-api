package com.specialized.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

//@Component
public class AuthorizationRequestFilter implements Filter {

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException  {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) res;
        
        String[] auth = getAuth(httpRequest.getHeader("Authorization"));
        if (auth == null || auth.length != 2) throw new UnauthorizedException("Missing or invalid authorization header");
        
        System.out.println(auth);
        
        httpResponse.setHeader("X-Clacks-Overhead", "GNU Terry Pratchett");
        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {}
    
    private String[] getAuth(String auth) {
        if (!StringUtils.containsIgnoreCase(auth, "basic ")) return null;

        byte[] bytes = DatatypeConverter.parseBase64Binary(auth.replaceFirst("[Bb]asic ", ""));
        return StringUtils.split(new String(bytes), ":");
    }
}

@ResponseStatus(HttpStatus.CONFLICT)
class UnauthorizedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UnauthorizedException(String message) {
        super(message);
    }
}
