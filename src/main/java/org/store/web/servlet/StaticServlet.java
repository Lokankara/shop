package org.store.web.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StaticServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        System.out.println(path);
        request.getRequestDispatcher("/resource/css/styles.css/**").forward(request, response);
        RequestDispatcher requestDispatcher = getServletContext().getNamedDispatcher("default");
        HttpServletRequest wrapped = new HttpServletRequestWrapper(request) {
            public String getServletPath() {
                return "";
            }
        };
        requestDispatcher.forward(wrapped, response);
    }
}