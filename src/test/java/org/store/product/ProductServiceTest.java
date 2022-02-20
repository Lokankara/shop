package org.store.product;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.store.product.dao.ProductDao;
import org.store.product.dao.jdbc.JdbcProductDao;
import org.store.product.service.ProductService;

import static org.mockito.Mockito.*;

class ProductServiceTest {

    private static ProductService productService;
    private static ProductDao mockDao;

    @BeforeAll
    static void beforeAll() {
        mockDao = mock(JdbcProductDao.class);
        productService = new ProductService(mockDao);
    }

    @Test
    void testFindAllInvokes() {
        productService.findAll();
        verify(mockDao, times(1)).findAll();
    }

//    @Test
//    void testSaveInvokes() {
//        Product product = new Product(10, "test", "save", 10, LocalDateTime.now());
//        productService.save(product);
//        verify(mockDao, times(1)).save(product);
//    }
}
