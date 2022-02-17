package org.store.products;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductJdbcDaoTest {
    ProductJdbcDao productJdbcDao = new ProductJdbcDao();

    @Test
    void testFindAllIsNotEmpty() {
        List<Product> products = productJdbcDao.findAll();
        assertFalse(products.isEmpty());
    }

    @Test
    void testFindAllIsNotNull() {
        List<Product> products = productJdbcDao.findAll();
        products.forEach(product -> {
        assertNotEquals(0, product.getId());
        assertNotNull(product.getName());
        assertNotNull(product.getPrice());
        assertNotNull(product.getDescription());
        assertNotNull(product.getDate());
        });
    }

//    @Test
//    void testSaveReturnTrue() {
//        Product product = new Product(10, "test", "save", 10, LocalDateTime.now());
//        assertTrue(productJdbcDao.save(product));
//    }

}