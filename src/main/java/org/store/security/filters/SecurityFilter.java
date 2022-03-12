package org.store.security.filters;

import lombok.AllArgsConstructor;
import org.store.service.SecurityService;

import javax.servlet.*;
import java.io.IOException;

@AllArgsConstructor
public class SecurityFilter implements Filter {

    private final SecurityService securityService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }
}
