package org.store.web.servlet;

import lombok.AllArgsConstructor;
import org.store.exception.ProductNotFoundException;
import org.store.service.ProductService;
import org.store.web.entity.Product;
import org.store.web.template.PageMaker;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.lang.Long.parseLong;

@AllArgsConstructor
public class ProductServlet extends HttpServlet {

    private static final String filename = "products.html";

    private static final PageMaker PAGE_MAKER = PageMaker.instance();

    private final ProductService productService;
//    private static final String filename = "add.html";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        List<Product> productList = productService.findAll();
        Map<String, List<Product>> model = new HashMap<>();
        model.put("products", productList);
        try {
            response.getWriter().println(
                    PAGE_MAKER.getPage(filename, model));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response) {
        Optional<Product> optionalProduct = getProduct(request);
        optionalProduct.ifPresent(productService::saveProduct);
        optionalProduct.orElseThrow(() -> new ProductNotFoundException("Product not Saved"));
//        updateData(response);
    }

    @Override
    protected void doPut(HttpServletRequest request,
                         HttpServletResponse response) {
        Optional<Product> optionalProduct = getProduct(request);
        optionalProduct.ifPresent(productService::updateProduct);
        optionalProduct.orElseThrow(() -> new ProductNotFoundException("Product not Updated"));
//        updateData(response);
    }

    @Override
    protected void doDelete(
            HttpServletRequest request,
            HttpServletResponse response) {
        String[] result = request.getRequestURI().split("/");
        Long id = Long.valueOf(((result[result.length - 1])));
        productService.deleteProductBy(id);
//        updateData(response);
    }

    private Optional<Product> getProduct(HttpServletRequest request) {
        Product product = Product.builder()
                .name(request.getParameter("name"))
                .description(request.getParameter("description"))
                .price(Double.parseDouble(request.getParameter("price")))
                .build();
        return Optional.of(product);
    }

//    private void updateData(HttpServletResponse response) {
////        Set<Long> keys = productService.findAllIds();
////        Map<Long, Product> productsFromDb = productService.findAll();
//        Map<String, List<Product>> model = new HashMap<>();
////        List<Product> productsList = new ArrayList<>(productsFromDb.values());
//        List<Product> productsList = productService.findAll();
//        model.put("products", productsList);

//        try {
//            PageGenerator.writePage(response.getWriter(),
//                    filename, productsFromDb);
//        } catch (IOException exception) {
//            throw new ProductNotFoundException(
//                    exception.getMessage(), exception);
//        }
}