package org.store.product.service;

import org.store.exception.ProductNotFoundException;
import org.store.product.dao.ProductDao;
import org.store.product.web.domain.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProductService {

    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public Map<Long, Product> findAll() {
        return productDao.findAll();
    }

    public int update(Product product) {
        return productDao.update(product);
    }

    public int deleteById(Long id) {
        return productDao.delete(id);
    }

    public Product findProductById(Long id) {
        Optional<Product> optionalProduct = productDao.findById(id);
        return optionalProduct.orElseThrow(() -> new ProductNotFoundException("Product By Id not founded"));
    }

    public int saveProduct(Product product) {
        return productDao.saveProduct(product);
    }
}
