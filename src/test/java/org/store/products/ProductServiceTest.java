package org.store.products;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

class ProductServiceTest {

    private static ProductService productService;
    private static ProductDao mockDao;

    @BeforeAll
    static void beforeAll() {
        mockDao = mock(ProductJdbcDao.class);
        productService = new ProductService(mockDao);
    }

    @Test
    void testFindAllInvokes() {
        productService.findAll();
        verify(mockDao, times(1)).findAll();
    }

    @Test
    void testSaveInvokes() {
        Product product = new Product(10, "test", "save", 10, LocalDateTime.now());
        productService.save(product);
        verify(mockDao, times(1)).save(product);
    }
}
