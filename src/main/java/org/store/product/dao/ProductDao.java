package org.store.product.dao;

import org.store.product.web.domain.Product;

import java.util.List;

public interface ProductDao {

    List<Product> findAll();

    int update(Product product);

    int delete(int id);
}
