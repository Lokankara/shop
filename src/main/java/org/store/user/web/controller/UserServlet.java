package org.store.user.web.controller;

import org.store.secure.registry.RegistryService;
import org.store.user.service.UserService;
import org.store.user.web.domain.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class UserServlet extends HttpServlet {

    private final UserService userService;

    public UserServlet(UserService userService, RegistryService registryService) {
        this.userService = userService;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        User user = getUser(request);
        userService.updateUser(user);
        try {
            response.sendRedirect("/users");
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    private User getUser(HttpServletRequest request) {
        return User.builder()
                .name(request.getParameter("name"))
                .email(request.getParameter("email"))
                .password(request.getParameter("password"))
                .created(LocalDateTime.now())
                .build();
    }
}
