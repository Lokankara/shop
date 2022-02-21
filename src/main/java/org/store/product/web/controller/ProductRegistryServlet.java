package org.store.product.web.controller;

import org.store.product.service.RegistryService;
import org.store.product.web.domain.Product;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class ProductRegistryServlet extends HttpServlet {

    private final RegistryService registryService;

    public ProductRegistryServlet(RegistryService registryService) {
        this.registryService = registryService;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        Product product = getProduct(request);
        registryService.save(product);
        try {
            response.sendRedirect("/products");
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage(),exception);
        }
    }

    private Product getProduct(HttpServletRequest request) {
        return Product.builder()
                .name(request.getParameter("name"))
                .price(Double.parseDouble(request.getParameter("price")))
                .description(request.getParameter("description"))
                .date(LocalDateTime.now())
                .build();
    }
}
