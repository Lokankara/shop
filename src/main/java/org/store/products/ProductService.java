package org.store.products;

import java.util.List;

public class ProductService {

    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> findAll() {
        return productDao.findAll();
    }

    boolean save(Product product){return  productDao.save(product);};
}
