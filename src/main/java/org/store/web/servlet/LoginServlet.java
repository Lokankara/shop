package org.store.web.servlet;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.store.service.SecurityService;
import org.store.web.entity.User;
import org.store.web.utils.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Optional;

import static org.store.web.utils.WebUtils.userMapper;

@RequiredArgsConstructor
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
        Optional<User> user = userMapper(request);
        HttpSession sessionStorage = request.getSession();
        securityService.setSession(user, sessionStorage);
//        sessionStorage.setAttribute("session", session);
        response.sendRedirect("/products");
    }
}