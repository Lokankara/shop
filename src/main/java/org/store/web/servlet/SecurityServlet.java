package org.store.web.servlet;

import lombok.AllArgsConstructor;
import org.store.exception.UserNotFoundException;
import org.store.service.SecurityService;
import org.store.service.UserService;
import org.store.web.entity.User;
import org.store.web.utils.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

@AllArgsConstructor
public class SecurityServlet extends HttpServlet {

    private SecurityService securityService;
    private UserService userService;
    private static final String filename = "index.html";

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) {
        boolean valid = securityService.isAuthentication(request);
        if (!valid) {
            try {
                PageGenerator.getPage(filename, response.getWriter(), new HashMap<>());
            } catch (IOException exception) {
                throw new RuntimeException(exception.getMessage(), exception);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        Optional<User> optionalUser = userService.getUser(request);

        User userRequest = (optionalUser.orElseThrow(() ->
                new UserNotFoundException("The User is not Registered")));

        boolean authentication =
                securityService.isAuthentication(request);

        userRequest.setAuth(!authentication);

//        boolean authorization = securityService.isAuthorization(userRequest, token);

        try {
            response.sendRedirect("/products");
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }
}