package org.store.products;

import java.util.List;

public interface ProductDao {

    List<Product> findAll();

    boolean save(Product product);

    Product edit(Product product);

    boolean delete(int id);
}
