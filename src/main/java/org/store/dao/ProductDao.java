package org.store.dao;

import org.store.web.entity.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface ProductDao {

    Optional<Product> findBy(Long id);

    List<Product> findAll();

//    Set<Long> findAllKeys();

    boolean save(Product product);

    boolean update(Product product);

    boolean deleteBy(Long id);
}
