package org.store.web.servlet;

import lombok.AllArgsConstructor;
import org.store.service.ProductService;
import org.store.web.entity.Product;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.store.web.utils.Getters.productMapper;

@AllArgsConstructor
public class ProductServlet extends HttpServlet {

    private final ProductService productService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        Product product = productMapper(request);
        productService.saveProduct(product);
        try {
            response.sendRedirect("/products");
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.parseLong(request.getParameter("id"));
        productService.deleteProduct(id);
        try {
            response.sendRedirect("/products");
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }
}