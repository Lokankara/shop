package org.store.web.servlet;

import lombok.AllArgsConstructor;
import org.store.service.SecurityService;
import org.store.web.entity.Session;
import org.store.web.utils.PageGenerator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

import static org.store.service.SecurityService.generateUUID;

@AllArgsConstructor
public class SecurityServlet extends HttpServlet {

    private final SecurityService securityService;
    private final Session session;

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
        Object sessionStorage = request.getAttribute("session");
        System.out.println(sessionStorage.toString());

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user-token")) {
                session.setToken((cookie.getValue()));
            } else {
                cookie = new Cookie("user-token", generateUUID());
                cookie.setMaxAge(300);
                session.getUser().setAuth(true);
                session.setToken(cookie.getValue());
                response.addCookie(cookie);
            }
//
//            sessionStorage.
//                    User user = request.getAttribute("");
//            securityService.checkUser(user);
            try {
                response.sendRedirect("/products");
            } catch (IOException exception) {
                throw new RuntimeException(exception.getMessage(), exception);
            }
        }
    }
}