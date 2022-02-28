package org.store.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.store.dao.ProductDao;
import org.store.dao.jdbc.JdbcProductDao;
import org.store.service.ProductService;
import org.store.web.entity.Product;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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

    @Test
    void testFindAllInvokes() {
        productService.findAll();
        verify(mockDao).findAll();
    }

    @Test
    void testSaveAllInvokes() {
        productService.saveProduct(product);
        when(mockDao.save(product)).thenReturn(true);
        verify(mockDao).save(product);
        assertTrue(mockDao.save(product));
    }

    @Test
    void testEditInvokesReturnInt() {
        boolean result = productService.updateProduct(product);
        assertTrue(result);
        verify(mockDao).update(product);
    }

//    @Test
//    void testFindAll() {
//        Map<Long, Product> productList = new HashMap<>();
//        productList.put(product.getId(), product);
//        productList.put(product2.getId(), product2);
//        when(productService.findAll()).thenReturn(productList);
//        Map<Long, Product> actual = productService.findAll();
//        productList.forEach(actual::remove);
//        assertEquals(2, actual.size());
//        verify(mockDao).findAll();
//    }
}
