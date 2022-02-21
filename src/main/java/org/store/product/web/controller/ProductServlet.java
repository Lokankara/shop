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
    private static final String filename = "products.html";
    private final ProductService productService;

    public ProductServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        List<Product> productList = productService.findAll();
        Map<String, List<Product>> model = new HashMap<>();
        model.put("products", productList);
        response.getWriter().println(
                PAGE_MAKER.getPage(filename, model));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        Product product = getProduct(request);
        productService.update(product);
        try {
            response.sendRedirect("/products");
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        String[] result = request.getRequestURI().split("/");
        int id = (Integer.parseInt(result[result.length - 1]));
        productService.deleteById(id);
        try {
            response.sendRedirect("/products");
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    private Product getProduct(HttpServletRequest request) {
        return Product.builder()
                .id(Integer.parseInt(request.getParameter("id")))
                .name(request.getParameter("name"))
                .price(Double.parseDouble(request.getParameter("price")))
                .description(request.getParameter("description"))
                .date(LocalDateTime.now())
                .build();
    }
}