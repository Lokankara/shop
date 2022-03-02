package org.store.web.servlet;

import lombok.AllArgsConstructor;
import org.store.service.ProductService;
import org.store.web.entity.Product;
import org.store.web.utils.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.store.web.utils.Getters.productMapper;

@AllArgsConstructor
public class ProductServlet extends HttpServlet {

    private static final String filename = "products.html";
    private static final PageGenerator PAGE_MAKER = new PageGenerator();
    private final ProductService productService;
    //private static final String filename = "add.html";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        Product product = productMapper(request);
        productService.updateProduct(product);
        try {
            response.sendRedirect("/products");
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.parseLong(request.getParameter("id"));
        productService.deleteProductBy(id);
        try {
            response.sendRedirect("/products");
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }
}