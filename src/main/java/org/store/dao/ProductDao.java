package org.store.dao;

import org.store.web.entity.Product;

import java.util.List;
import java.util.Set;

public interface ProductDao {

    List<Product> findAll();

    boolean save(Product product);

    boolean delete(Long id);

    List<Product> findByIds(List<Long> ids);
}
