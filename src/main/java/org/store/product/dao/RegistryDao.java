package org.store.product.dao;

import org.store.product.web.domain.Product;

public interface RegistryDao {
    int save(Product product);
}
