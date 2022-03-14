package org.store.web.servlet;

import lombok.AllArgsConstructor;
import org.store.service.SecurityService;
import org.store.web.entity.User;
import org.store.web.utils.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

import static org.store.web.utils.Getters.userMapper;

@AllArgsConstructor
public class SecurityServlet extends HttpServlet {

    private final SecurityService securityService;
    private static final String filename = "index.html";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            PageGenerator.getPage(filename, response.getWriter(), new HashMap<>());
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        User user = userMapper(request).orElseThrow();
        securityService.checkUser(user);

        try {
            response.sendRedirect("/products");
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }
}