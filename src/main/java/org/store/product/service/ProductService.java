package org.store.product.service;

import org.store.product.dao.ProductDao;
import org.store.product.web.domain.Product;

import java.util.List;

public class ProductService {

    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;}

    public List<Product> findAll(){
        return productDao.findAll();}

    public int update(Product product){
        return productDao.update(product);};

    public int remove(Product product){
        return productDao.delete(product);};

    public int save(Product product){
        return productDao.save(product);};
}
