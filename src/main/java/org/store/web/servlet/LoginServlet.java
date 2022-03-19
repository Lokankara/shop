package org.store.web.servlet;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.store.service.SecurityService;
import org.store.web.entity.Session;
import org.store.web.utils.PageGenerator;

import javax.servlet.http.*;
import java.util.HashMap;

@AllArgsConstructor
public class LoginServlet extends HttpServlet {

    private final SecurityService securityService;
    private static final String filename = "index.html";

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute("admin", request.getParameter("admin"));
        }
        PageGenerator.getPage(filename, response.getWriter(), new HashMap<>());
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        HttpSession sessionStorage = request.getSession();
        String username = sessionStorage.getAttribute("username").toString();
        Cookie[] cookies = request.getCookies();
        Session session = securityService.setUserToken(username, cookies);
        sessionStorage.setAttribute("session", session);
        response.sendRedirect("/products");
    }
}