package org.store.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.store.dao.ProductDao;
import org.store.dao.jdbc.JdbcProductDao;
import org.store.web.entity.Product;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private static ProductService productService;
    private static ProductDao mockDao;
    private static Product product;
    private static List<Product> productList;

    @BeforeAll
    static void beforeAll() {
        mockDao = mock(JdbcProductDao.class);
        productService = new ProductService(mockDao);
        product = Product.builder().id(1L).name("product").description("test").price(10.00).created(LocalDateTime.now()).build();
        Product apple = Product.builder().id(10L).name("apple").description("red").price(20.00).created(LocalDateTime.now()).build();
        Product orange = Product.builder().id(11L).name("orange").description("green").price(20.00).created(LocalDateTime.now()).build();
        productList = List.of(apple, orange);
    }

    @Test
    @DisplayName(value = "Test save invokes and return true")
    void saveProduct() {
        when(mockDao.save(product)).thenReturn(true);
        boolean isSaved = productService.saveProduct(product);
        verify(mockDao).save(product);
        assertTrue(isSaved);
    }

    @Test
    @DisplayName(value = "Test get all products and return list of products")
    void findAll() {
        when(mockDao.findAll()).thenReturn(productList);
        List<Product> actual = productService.findAll();
        assertEquals(productList.size(), actual.size());
        verify(mockDao).findAll();
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }
}
