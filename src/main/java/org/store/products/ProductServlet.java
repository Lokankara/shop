package org.store.products;

import org.store.utils.PageMaker;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        ProductService productService = new ProductService(new ProductJdbcDao());
        List<Product> productList = productService.findAll();
        response.getWriter().println(PageMaker.instance()
                .getPage("products.html", productList));
    }

    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect("/products/add");
    }
}