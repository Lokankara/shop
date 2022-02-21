package org.store.product.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.store.product.dao.ProductDao;
import org.store.product.dao.jdbc.JdbcProductDao;
import org.store.product.web.domain.Product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private static ProductService productService;
    private static ProductDao mockDao;

    @BeforeAll
    static void beforeAll() {
        mockDao = mock(JdbcProductDao.class);
        productService = new ProductService(mockDao);
    }

//    @Test
//    void testFindAllInvokes() {
//        productService.findAll();
//        verify(mockDao).findAll();
//    }

    @Test
    void testEditInvokesReturnInt() {
        Product product = new Product(1, "apple", "golden", 10, LocalDateTime.now());
        int result = productService.update(product);
        assertEquals(0, result);
        verify(mockDao).update(product);
    }

    @Test
    void testFindAll() {
        Product product = new Product(10, "orange", "red", 100, LocalDateTime.now());
        Product product1 = new Product(10, "orange", "red", 100, LocalDateTime.now());
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productService.findAll()).thenReturn(productList);
        List<Product> actual = productService.findAll();
//        productList.forEach(actual::remove);
        assertEquals(1, actual.size());
        verify(mockDao).findAll();
    }
}
