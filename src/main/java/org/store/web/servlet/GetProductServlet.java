package org.store.web.servlet;

import lombok.AllArgsConstructor;
import org.store.exception.ProductNotFoundException;
import org.store.service.ProductService;
import org.store.web.entity.Product;
import org.store.web.utils.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static org.store.web.utils.Getters.getProduct;

@AllArgsConstructor
public class GetProductServlet extends HttpServlet {

    private static final String filename = "products.html";
    private static final PageGenerator PAGE_MAKER = new PageGenerator();
    private final ProductService productService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        List<Product> productList = productService.findAll();
        Map<String, List<Product>> model = new HashMap<>();
        model.put("products", productList);
        try {
            response.getWriter().println(
                    PAGE_MAKER.getPage(filename, model));
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        Optional<Product> optionalProduct = Optional.of(getProduct(request));
        if (optionalProduct.get().getId() != null) {
            optionalProduct.ifPresent(productService::updateProduct);
        } else {
            optionalProduct.ifPresent(productService::saveProduct);
        }
        optionalProduct.orElseThrow(() -> new ProductNotFoundException("Product not Saved"));
//        updateData(response);
    }

}