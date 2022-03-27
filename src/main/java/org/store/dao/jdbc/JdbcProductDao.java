package org.store.dao.jdbc;

import org.springframework.stereotype.Component;
import org.store.dao.ProductDao;
import org.store.dao.jdbc.template.JdbcProductTemplate;
import org.store.web.entity.Product;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JdbcProductDao implements ProductDao {

    private static final String INSERT_PRODUCT_SQL = "INSERT INTO products (name, description, price, created) VALUES (?, ?, ?, ?);";
    private static final String SELECT_ALL_SQL = "SELECT id, name, description, price, created FROM products;";
    private static final String UPDATE_SQL = "UPDATE products SET name =?, description =?, price =? WHERE id =?;";
    private static final String DELETE_BY_ID_SQL = "DELETE FROM products WHERE id=?;";
    private static final String SELECT_BY_IDS_SQL = "SELECT name, price FROM products WHERE FIND_IN_SET %s";
    private static final String SELECT_PRODUCT_BY_ID_SQL = "SELECT id, name, description, price, created FROM products WHERE id =?;";

    private final JdbcProductTemplate jdbcProductTemplate;

    public JdbcProductDao(JdbcProductTemplate jdbcProductTemplate) {
        this.jdbcProductTemplate = jdbcProductTemplate;
    }

    @Override
    public List<Product> findAll() {
        return jdbcProductTemplate.getProductsQuery(SELECT_ALL_SQL);
    }

    @Override
    public boolean delete(Long id) {
        return jdbcProductTemplate
                .deleteProductById(id, DELETE_BY_ID_SQL);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return jdbcProductTemplate
                .findProductByIdQuery(id, SELECT_PRODUCT_BY_ID_SQL);
    }

    public List<Product> findByIds(List<Long> ids) {
        String joinIds = ids.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(",", "(", ")"));
        String sql = String.format(SELECT_BY_IDS_SQL, joinIds);
        return jdbcProductTemplate.getProductsQuery(sql);
    }

    @Override
    public boolean save(Product product) {
        if (product.getId() == 0) {
            return jdbcProductTemplate
                    .setProductQuery(product, INSERT_PRODUCT_SQL);
        } else {
            return jdbcProductTemplate
                    .setProductQuery(product, UPDATE_SQL);
        }
    }
}
