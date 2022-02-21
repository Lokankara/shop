package org.store.product.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.store.product.dao.ProductDao;
import org.store.product.dao.jdbc.JdbcProductDao;
import org.store.product.web.domain.Product;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void testSaveAllInvokes() {
        Product product = Product.builder().
                id(10L)
                .name("orange")
                .price(20.00)
                .date(LocalDateTime.now())
                .build();
        productService.saveProduct(product);
        when(mockDao.saveProduct(product)).thenReturn(0);
        verify(mockDao).saveProduct(product);
        assertEquals(0, mockDao.saveProduct(product));
    }

    @Test
    void testEditInvokesReturnInt() {
        Product product = Product.builder().
                id(10L)
                .name("apple")
                .price(20.00)
                .date(LocalDateTime.now())
                .build();
        int result = productService.update(product);
        assertEquals(0, result);
        verify(mockDao).update(product);
    }

    @Test
    void testFindAll() {
        Product product = Product.builder().
                id(10L)
                .name("orange")
                .description("red")
                .price(20.00)
                .date(LocalDateTime.now())
                .build();

        Product product1 = Product.builder().
                id(11L)
                .name("orange")
                .description("green")
                .price(20.00)
                .date(LocalDateTime.now())
                .build();

        Map<Long, Product> productList = new HashMap<>();
        productList.put(product.getId(), product);
        productList.put(product1.getId(), product1);
        when(productService.findAll()).thenReturn(productList);
        Map<Long, Product> actual = productService.findAll();
//        productList.forEach(actual::remove);
//        assertEquals(2, actual.size());
        verify(mockDao).findAll();
    }
}
