package org.store.dao;

import org.store.web.entity.Product;

import java.util.List;

public interface ProductDao {

    List<Product> findAll();

    boolean save(Product product);

    boolean delete(Long id);

}
