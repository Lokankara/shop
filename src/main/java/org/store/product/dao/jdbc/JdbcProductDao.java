package org.store.product.dao.jdbc;

import org.store.product.dao.ProductDao;
import org.store.product.web.domain.Product;

import java.util.Map;
import java.util.Optional;

public class JdbcProductDao implements ProductDao {

    private static final String INSERT_PRODUCT_SQL = "INSERT INTO products (name, description, price) VALUES (?, ?, ?);";
    private static final String SELECT_ALL_SQL = "SELECT id, name, description, price, date FROM products LIMIT 100;";
    private static final String SELECT_BY_ID_SQL = "SELECT id, name, description, price, date FROM products WHERE id=?;";
    private static final String UPDATE_SQL = "UPDATE products SET name =?, description =?, price =? WHERE id =?;";
    private static final String DELETE_BY_ID_SQL = "DELETE FROM products WHERE id=?;";

    private final JdbcProductTemplate jdbcProductTemplate;

    public JdbcProductDao(JdbcProductTemplate jdbcProductTemplate) {
        this.jdbcProductTemplate = jdbcProductTemplate;
    }

    @Override
    public Map<Long, Product> findAll() {
        return jdbcProductTemplate.getProductQuery(SELECT_ALL_SQL);}

    @Override
    public int update(Product product) {
        return jdbcProductTemplate.setProductQuery(product, UPDATE_SQL);
    }

    @Override
    public int delete(Long id) {
        return jdbcProductTemplate.deleteProductById(id, DELETE_BY_ID_SQL);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return jdbcProductTemplate.findProductByIdQuery(id, SELECT_BY_ID_SQL);
    }

    @Override
    public int saveProduct(Product product) {
        return jdbcProductTemplate.setProductQuery(product, INSERT_PRODUCT_SQL);
    }

}
