package org.store.web.servlet;

import lombok.AllArgsConstructor;
import org.store.exception.ProductNotFoundException;
import org.store.service.ProductService;
import org.store.service.SecurityService;
import org.store.web.entity.Product;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class ProductsServlet extends HttpServlet {

    private static final String filename = "products.html";
//    private static final PageGenerator PAGE_MAKER = new PageGenerator();
    private final ProductService productService;

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
//        List<Product> productList = productService.findAll();
//        Map<String, List<Product>> model = new HashMap<>();
//        model.put("products", productList);
//        try {
//            response.getWriter().println(
//                    PAGE_MAKER.getPage(filename, model));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//        PageGenerator pageGenerator = PageGenerator.instance();
//        Map<Long, Product> products = productService.findAll();
//        HashMap<String, List<Product>> parameters = new HashMap<>();
//        parameters.put("products", products);
//        boolean isAuth = securityService.isAuthentication(request);
//        if (isAuth) {
//            parameters.put("isAuth", true);
//        }
//        String page = pageGenerator.getPage(filename, parameters);
//        response.getWriter().write(page);
}