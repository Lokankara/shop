package org.store.product.dao.jdbc;

import org.store.product.dao.RegistryDao;
import org.store.product.web.domain.Product;

public class JdbcRegistryDao implements RegistryDao {
    private static final String INSERT_INTO_SQL = "INSERT INTO products (name, description, price) VALUES (?, ?, ?);";

    private final JdbcTemplate jdbcTemplate;

    public JdbcRegistryDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Product product) {
        return jdbcTemplate.setQuery(product, INSERT_INTO_SQL);
    }
}
