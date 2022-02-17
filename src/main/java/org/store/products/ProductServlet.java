package org.store.products;

import org.store.utils.PageMaker;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServlet extends HttpServlet {
    private static final PageMaker PAGE_MAKER = PageMaker.instance();

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
            HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect("/products/add");
    }
}