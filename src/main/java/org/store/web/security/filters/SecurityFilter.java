package org.store.web.security.filters;

import lombok.AllArgsConstructor;
import org.store.service.SecurityService;
import org.store.web.entity.Session;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
public class SecurityFilter implements Filter {
    private final SecurityService securityService;
    private Session session;

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        List<String> allow = List.of("/products", "/static");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String sessionToken = session.getToken();
        System.out.println(session.getProductList());
        System.out.println(sessionToken);
        System.out.println(session.getUser().isAuth());


//        boolean isAuth = session.getUser().isAuth();


        if (true) {
            for (String path : allow) {
                if (request.getRequestURI().startsWith(path)) {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            }
        } else redirect(response);
    }

    private void redirect(HttpServletResponse response) {
        try {
            response.sendRedirect("/login");
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    @Override
    public void destroy() {
    }
}