//package org.store.product.dao.jdbc;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.store.secure.registry.config.PropertiesReader;
//import org.store.secure.registry.JdbcRegistryDao;
//import org.store.secure.registry.RegistryService;
//import org.store.product.web.domain.Product;
//import org.store.user.web.domain.User;
//
//import java.time.LocalDateTime;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.*;
//
//class JdbcRegistryDaoTest {
//
//    private static RegistryService registryService;
//    private static JdbcTemplate mockJdbcTemplate;
//
//    static ConnectionFactory connectionFactory =
//            new ConnectionFactory(new PropertiesReader("h2.properties").getProperties());
//
//    @BeforeAll
//    static void beforeAll() {
//        JdbcRegistryDao mock = mock(JdbcRegistryDao.class);
//        registryService = new RegistryService(mock);
//        mockJdbcTemplate = new JdbcTemplate(connectionFactory);
//    }
//
//    @Test
//    void save() {
//        Map< Product,Long> product;
////                        new Product(10, "kiwi", "green", 10, LocalDateTime.now());
//        registryService.save(product);
////        verify(mockJdbcTemplate).selectQuery("SELECT * FROM products;");
//    }
//
//    @Test
//    void testSaveInvokes() {
//        Product product = new Product(10, "kiwi", "green", 10, LocalDateTime.now());
//        registryService.save(product);
//        when(registryService.save(product)).thenReturn(1);
////        verify(mockJdbcTemplate, times(1)).insertQuery(product,"INSERT INTO products (name, description, price) VALUES ('kiwi', 'green', 10);");
//    }
//
//    @Test
//    void testFindAllInvokesAfterSave() {
//        Product product = new Product(10, "kiwi", "green", 10, LocalDateTime.now());
//        registryService.save(product);
////        verify(registryService).save(product);
//    }
//
//    @Test
//    void testSaveReturnTrue() {
//        Product product = new Product(10, "test", "save", 10, LocalDateTime.now());
//        when(registryService.save(product)).thenReturn(0);
//        assertNotNull(registryService.save(product));
//    }
//}