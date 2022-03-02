package org.store.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.store.dao.ProductDao;
import org.store.dao.jdbc.JdbcProductDao;
import org.store.web.entity.Product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private static ProductService productService;
    private static ProductDao mockDao;
    private static Product product;
    private static Product product2;

    @BeforeAll
    static void beforeAll() {
        mockDao = mock(JdbcProductDao.class);
        productService = new ProductService(mockDao);
        product = Product.builder().id(10L).name("orange").description("red").price(20.00).created(LocalDateTime.now()).build();
        product2 = Product.builder().id(11L).name("orange").description("green").price(20.00).created(LocalDateTime.now()).build();
    }

//    @Test
//    void testFindAllInvokes() {
//        productService.findAll();
//        verify(mockDao).findAll();
//    }

    @Test
    void testSaveAllInvokes() {
        productService.saveProduct(product);
        when(mockDao.save(product)).thenReturn(true);
        verify(mockDao).save(product);
        assertTrue(mockDao.save(product));
    }
}
