package org.store.web.servlet;

import lombok.AllArgsConstructor;
import org.store.exception.UserNotFoundException;
import org.store.service.SecurityService;
import org.store.web.entity.User;
import org.store.web.utils.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@AllArgsConstructor
public class SecurityServlet extends HttpServlet {

    private SecurityService securityService;
    private static final String filename = "index.html";
    private static final PageGenerator PAGE_MAKER = new PageGenerator();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) {
        boolean valid = securityService.isAuthentication(request);
        if (!valid) {
            try {
                response.getWriter().println(
                        PAGE_MAKER.getPage(filename, new HashMap<>()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        User user = securityService.getUser(request);

        User userDb = securityService.findUserByName(user.getUsername())
                .orElseThrow(() ->
                        new UserNotFoundException("The User is not Registered"));

        boolean authentication =
                securityService.isAuthentication(request);

        userDb.setAuth(!authentication);

        try {
            response.sendRedirect("/products");
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }
}