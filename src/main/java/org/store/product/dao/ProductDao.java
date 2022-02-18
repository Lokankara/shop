package org.store.product.dao;

import org.store.product.web.domain.Product;

import java.util.List;

public interface ProductDao {

    List<Product> findAll();

    int save(Product product);

    int update(Product product);

    int delete(Product product);
}
