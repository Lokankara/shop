package org.store.web.servlet;

import lombok.AllArgsConstructor;
import org.store.service.UserService;
import org.store.web.entity.Session;
import org.store.web.utils.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@AllArgsConstructor
public class CartServlet extends HttpServlet {
    private final UserService userService;
    private final Session session;

    private static final String filename = "cart.html";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PageGenerator.getPage(filename, response.getWriter(), new HashMap<>());
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long product_id = Long.valueOf(request.getParameter("id"));
        Cookie[] cookies = request.getCookies();
        boolean isValid = false;
        for (Cookie cookie : cookies) {
            if ("user-token".equals(cookie.getName())) {
                isValid = true;
            }
            break;
        }
        if (isValid) {

            userService.addToCart(session, product_id);
        }
    }
}