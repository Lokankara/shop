package org.store.web.security.filters;

import lombok.AllArgsConstructor;
import org.store.service.SecurityService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
public class SecurityFilter implements Filter {

    private final SecurityService securityService;

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        List<String> allow = List.of("/products", "/static");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        securityService.isAuthentication(request, response);

        for (String path : allow) {
            if (request.getRequestURI().startsWith(path)) {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void destroy() {
    }
}