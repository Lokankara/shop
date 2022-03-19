package org.store.web.servlet;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.store.service.SecurityService;
import org.store.web.entity.User;
import org.store.web.utils.PageGenerator;

import javax.servlet.http.*;
import java.util.HashMap;

import static org.store.web.utils.WebUtils.userMapper;

@AllArgsConstructor
public class LoginServlet extends HttpServlet {

    private final SecurityService securityService;
    private static final String filename = "index.html";

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        PageGenerator.getPage(filename, response.getWriter(), new HashMap<>());
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        User user = userMapper(request).orElseThrow();
        HttpSession sessionStorage = request.getSession();
        securityService.setSession(user, sessionStorage);
//        sessionStorage.setAttribute("session", session);
        response.sendRedirect("/products");
    }
}