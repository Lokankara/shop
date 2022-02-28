package org.store.service;

import org.store.dao.ProductDao;
import org.store.exception.ProductNotFoundException;
import org.store.web.entity.Product;

import java.util.List;
import java.util.Optional;

public class ProductService {

    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> findAll() {
        return productDao.findAll();
    }

    public boolean saveProduct(Product product) {
        return productDao.save(product);
    }

    public boolean updateProduct(Product product) {
        return productDao.update(product);
    }

    public boolean deleteProductBy(Long id) {
        return productDao.deleteBy(id);
    }

    public Product findProductBy(Long id) {
        Optional<Product> optionalProduct =
                productDao.findBy(id);

        return optionalProduct.orElseThrow(() ->
                new ProductNotFoundException("Product By Id not founded"));
    }

//    public Set<Long> findAllProductIds() {
//        return productDao.findAllKeys();
//    }
}
