package org.store.security.filters;

import lombok.AllArgsConstructor;
import org.store.service.SecurityService;
import org.store.service.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
public class SecurityFilter implements Filter {

    private final SecurityService securityService;

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        boolean authentication = securityService.isAuthentication(request);

        if (!authentication) {
            try {
                response.sendRedirect("/login");
            } catch (IOException exception) {
                throw new RuntimeException(exception.getMessage(), exception);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
//getUser(request);
//        userRequest.setAuth(!authentication);
//        boolean authorization = securityService.isAuthorization(userRequest, token);