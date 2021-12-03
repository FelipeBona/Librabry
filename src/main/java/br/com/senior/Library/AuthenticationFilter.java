package br.com.senior.Library;

import br.com.senior.Library.user.UserServiceProd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Profile("prod")
@Component
@Order(1)
public class AuthenticationFilter implements Filter {

    @Autowired
    public UserServiceProd userService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final String authorization = request.getHeader("Authorization");
        final String uri = request.getRequestURI();

        if((userService.tokens.contains(authorization) && authorization != null) || uri.equals("/login")){
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            final HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido");
        }
    }
}
