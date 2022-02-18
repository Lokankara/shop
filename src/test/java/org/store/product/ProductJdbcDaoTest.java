package org.store.product;

import org.junit.jupiter.api.Test;
import org.store.config.PropertiesReader;
import org.store.product.dao.ConnectionFactory;
import org.store.product.dao.JdbcTemplate;
import org.store.product.dao.ProductJdbcDao;
import org.store.product.web.domain.Product;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductJdbcDaoTest {
    ConnectionFactory connectionFactory =
            new ConnectionFactory(new PropertiesReader().getProperties());

    ProductJdbcDao productJdbcDao = new ProductJdbcDao(new JdbcTemplate(connectionFactory));

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

    @Test
    void testSaveReturnTrue() {
        Product product = new Product(10, "test", "save", 10, LocalDateTime.now());
        assertNotNull(productJdbcDao.save(product));
    }

}