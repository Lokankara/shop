package org.store.product.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.store.product.dao.RegistryDao;
import org.store.product.dao.jdbc.JdbcRegistryDao;
import org.store.product.web.domain.Product;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RegistryServiceTest {

    private static RegistryService registryService;
    private static RegistryDao mockDao;
    private static Product product;


    @BeforeAll
    static void beforeAll() {
        mockDao = mock(JdbcRegistryDao.class);
        registryService = new RegistryService(mockDao);
        product = Product.builder().
                id(10)
                .name("orange")
                .price(20.00)
                .date(LocalDateTime.now())
                .build();
    }

    @Test
    void testSaveAllInvokes() {
        registryService.save(product);
        when(mockDao.save(product)).thenReturn(0);
        verify(mockDao).save(product);
        assertEquals(0, mockDao.save(product));
    }
}

