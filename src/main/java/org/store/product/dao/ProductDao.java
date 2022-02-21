package org.store.product.dao;

import org.store.product.web.domain.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductDao {

    Map<Long, Product> findAll();

    int update(Product product);

    int delete(Long id);

    Optional<Product> findById(Long id);

    int saveProduct(Product product);
}
