package org.store.product.web.controller;

import org.store.product.service.ProductService;
import org.store.product.service.RegistryService;
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

public class ProductRegistryServlet extends HttpServlet {

    private static final PageGenerator PAGE_MAKER = new PageGenerator();
    private static final String filename = "add.html";
    private final ProductService productService;

    public ProductRegistryServlet(ProductService productService) {
        this.productService = productService;
    }

//    private final RegistryService registryService;
//    public ProductRegistryServlet(RegistryService registryService) {
//        this.registryService = registryService;
//    }

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            Map<String, List<Product>> model = new HashMap<>();
//            response.getWriter().println(
//                    PAGE_MAKER.getPage(filename, model));
//        } catch (IOException exception) {
//            throw new RuntimeException(exception.getMessage(), exception);
//        }
//    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Product product = getProduct(request);
        productService.save(product);
        response.sendRedirect(request.getContextPath() + "/products");
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
