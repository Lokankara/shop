package org.store.product.web.controller;

import org.store.exception.ProductNotFoundException;
import org.store.product.service.ProductService;
import org.store.product.web.domain.Product;
import org.store.product.web.template.PageGenerator;
import org.store.user.service.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServlet extends HttpServlet {

    private static final PageGenerator PAGE_MAKER = new PageGenerator();
    private static final String filename = "products.html";
    private final ProductService productService;
    private final UserService userService;


    public ProductServlet(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response) {
        refresh(response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        Product product = getProduct(request);
        productService.saveProduct(product);
        refresh(response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        Product product = getProduct(request);
        productService.update(product);
        refresh(response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        String[] result = request.getRequestURI().split("/");
        Long id = Long.valueOf(((result[result.length - 1])));
        productService.deleteById(id);
    }

    private Product getProduct(HttpServletRequest request) {
        return Product.builder()
                .id((long) Integer.parseInt(request.getParameter("id")))
                .name(request.getParameter("name"))
                .price(Double.parseDouble(request.getParameter("price")))
                .description(request.getParameter("description"))
                .date(LocalDateTime.now())
                .build();
    }

    private void refresh(HttpServletResponse response) {
        Map<Long, Product> map = productService.findAll();
        Map<String, List<Product>> model = new HashMap<>();
        List<Product> products = new ArrayList(map.values());
        model.put("products", products);
        try {
            response.getWriter().println(
                    PAGE_MAKER.getPage(filename, model));
        } catch (IOException exception) {
            throw new ProductNotFoundException(exception.getMessage(), exception);
        }
    }

}