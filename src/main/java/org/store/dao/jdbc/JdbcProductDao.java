package org.store.dao.jdbc;

import org.store.dao.ProductDao;
import org.store.dao.jdbc.template.JdbcProductTemplate;
import org.store.web.entity.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class JdbcProductDao implements ProductDao {

    private static final String SELECT_ALL_IDS_SQL = "SELECT id FROM products;";
    private static final String INSERT_PRODUCT_SQL = "INSERT INTO products (name, description, price, created) VALUES (?, ?, ?, ?);";
    private static final String SELECT_ALL_SQL = "SELECT id, name, description, price, created FROM products LIMIT 100;";
    private static final String SELECT_BY_ID_SQL = "SELECT id, name, description, price FROM products WHERE id=?;";
    private static final String UPDATE_SQL = "UPDATE products SET name =?, description =?, price =? WHERE id =?;";
    private static final String DELETE_BY_ID_SQL = "DELETE FROM products WHERE id=?;";

    private final JdbcProductTemplate jdbcProductTemplate;

    public JdbcProductDao(JdbcProductTemplate jdbcProductTemplate) {
        this.jdbcProductTemplate = jdbcProductTemplate;
    }

    @Override
    public List<Product> findAll() {
        return jdbcProductTemplate
                .getProductsQuery(SELECT_ALL_SQL);
    }

//    @Override
//    public Set<Long> findAllKeys() {
//        return jdbcProductTemplate
//                .getProductsQuery(SELECT_ALL_IDS_SQL).keySet();
//    }

    @Override
    public int update(Product product) {
        return jdbcProductTemplate
                .setProductQuery(product, UPDATE_SQL);
    }

    @Override
    public int deleteBy(Long id) {
        return jdbcProductTemplate
                .deleteProductById(id, DELETE_BY_ID_SQL);
    }

    @Override
    public Optional<Product> findBy(Long id) {
        return jdbcProductTemplate
                .findProductByIdQuery(id, SELECT_BY_ID_SQL);
    }

    @Override
    public int save(Product product) {
        return jdbcProductTemplate
                .setProductQuery(product, INSERT_PRODUCT_SQL);
    }
}
