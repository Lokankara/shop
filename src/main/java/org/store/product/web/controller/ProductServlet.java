package org.store.product.web.controller;

import org.store.product.service.ProductService;
import org.store.product.web.domain.Product;
import org.store.product.web.template.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServlet extends HttpServlet {
    private static final PageGenerator PAGE_MAKER = new PageGenerator();

    private final ProductService productService;

    public ProductServlet(ProductService productService) {
        this.productService = productService;
    }


    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        List<Product> productList = productService.findAll();
        Map<String, Object> model = new HashMap<>();
        model.put("products", productList);
        response.getWriter().println(PAGE_MAKER.getPage("products.html", model));
    }

    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response) {
        Product product = getProduct(request);
        productService.save(product);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        Product product = getProduct(request);
        productService.update(product);
    }

    @Override
    protected void doDelete(HttpServletRequest request,
                            HttpServletResponse response) {
        Product product = getProduct(request);
        productService.remove(product);
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