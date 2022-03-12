package org.store.web.servlet;

import lombok.AllArgsConstructor;
import org.store.service.ProductService;
import org.store.web.entity.Product;
import org.store.web.utils.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.store.web.utils.Getters.productMapper;

@AllArgsConstructor
public class AllProductServlet extends HttpServlet {

    private static final String filename = "products.html";
    private final ProductService productService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        refresh(response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        Optional<Product> optionalProduct = Optional.of(productMapper(request));
        optionalProduct.ifPresent(productService::saveProduct);
        refresh(response);
    }

    private void refresh(HttpServletResponse response) {
        List<Product> productList = productService.findAll();
        Map<String, List<Product>> model = new HashMap<>();
        model.put("products", productList);
        try {
            PageGenerator.getPage(filename, response.getWriter(), model);
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

}