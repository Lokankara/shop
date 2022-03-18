package org.store.web.servlet;

import lombok.AllArgsConstructor;
import org.store.service.ProductService;
import org.store.web.entity.Product;
import org.store.web.entity.Session;
import org.store.web.utils.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class CartServlet extends HttpServlet {

    private final ProductService productService;
    private final Session session;

    private static final String filename = "cart.html";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        List<Product> products = session.getProductList();
        Map<String, List<Product>> model = new HashMap<>();
        model.put("cartItem", products);
        try {
            PageGenerator.getPage(filename, response.getWriter(), model);
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.parseLong(request.getParameter("id"));
        //TODO INFO: SELECT id, name, description, price, created FROM products WHERE id =85
        Product product = productService.findById(id).orElseThrow();
        List<Product> productList = session.getProductList();
        productList.add(product);
        session.setProductList(productList);
    }
}