package org.store.product.dao.jdbc;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.store.config.PropertiesReader;
import org.store.product.service.RegistryService;
import org.store.product.web.domain.Product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class JdbcRegistryDaoTest {

    private static RegistryService registryService;
    private static JdbcTemplate mockJdbcTemplate;

    static ConnectionFactory connectionFactory =
            new ConnectionFactory(new PropertiesReader("h2.properties").getProperties());

    @BeforeAll
    static void beforeAll() {
        JdbcRegistryDao mock = mock(JdbcRegistryDao.class);
        registryService = new RegistryService(mock);
        mockJdbcTemplate = new JdbcTemplate(connectionFactory);
    }

    @Test
    void save() {
        Product product = new Product(10, "kiwi", "green", 10, LocalDateTime.now());
        registryService.save(product);
//        verify(mockJdbcTemplate).selectQuery("SELECT * FROM products;");
    }

    @Test
    void testSaveInvokes() {
        Product product = new Product(10, "kiwi", "green", 10, LocalDateTime.now());
        registryService.save(product);
        when(registryService.save(product)).thenReturn(1);
//        verify(mockJdbcTemplate, times(1)).insertQuery(product,"INSERT INTO products (name, description, price) VALUES ('kiwi', 'green', 10);");
    }

    @Test
    void testFindAllInvokesAfterSave() {
        Product product = new Product(10, "kiwi", "green", 10, LocalDateTime.now());
        registryService.save(product);
//        verify(registryService).save(product);
    }

    @Test
    void testSaveReturnTrue() {
        Product product = new Product(10, "test", "save", 10, LocalDateTime.now());
        when(registryService.save(product)).thenReturn(0);
        assertNotNull(registryService.save(product));
    }
}