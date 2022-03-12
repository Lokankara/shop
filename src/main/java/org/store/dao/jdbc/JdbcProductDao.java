package org.store.dao.jdbc;

import org.store.dao.ProductDao;
import org.store.dao.jdbc.template.JdbcProductTemplate;
import org.store.web.entity.Product;

import java.util.List;

public class JdbcProductDao implements ProductDao {

    private static final String INSERT_PRODUCT_SQL = "INSERT INTO products (name, description, price, created) VALUES (?, ?, ?, ?);";
    private static final String SELECT_ALL_SQL = "SELECT id, name, description, price, created FROM products;";
    private static final String UPDATE_SQL = "UPDATE products SET name =?, description =?, price =? WHERE id =?;";
    private static final String DELETE_BY_ID_SQL = "DELETE FROM products WHERE id=?;";
    private final JdbcProductTemplate jdbcProductTemplate;

    public JdbcProductDao(JdbcProductTemplate jdbcProductTemplate) {
        this.jdbcProductTemplate = jdbcProductTemplate;
    }

    @Override
    public List<Product> findAll() {
        return jdbcProductTemplate.getProductsQuery(SELECT_ALL_SQL);
    }

    @Override
    public boolean update(Product product) {
        return jdbcProductTemplate
                .updateProductQuery(product, UPDATE_SQL);
    }

    @Override
    public boolean delete(Long id) {
        return jdbcProductTemplate
                .deleteProductById(id, DELETE_BY_ID_SQL);
    }

    @Override
    public boolean save(Product product) {
        return jdbcProductTemplate
                .setProductQuery(product, INSERT_PRODUCT_SQL);
    }
}
