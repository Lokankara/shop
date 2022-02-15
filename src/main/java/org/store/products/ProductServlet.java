package org.store.products;

import org.store.utils.PageMaker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static org.store.utils.Temp.fetch;

public class ProductServlet extends HttpServlet {

    private ProductService productService;


    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        ProductMapper productMapper = new ProductMapper(request, response);
        Map<String, Object> model = fetch();
        response.getWriter().println(PageMaker.instance().getPage("products.html", model));
    }

    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect("/products");
    }


    @Override
    protected void service(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.service(request, response);
    }
}