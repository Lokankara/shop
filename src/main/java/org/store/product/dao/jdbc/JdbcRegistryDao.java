package org.store.product.dao.jdbc;

import org.store.product.dao.RegistryDao;
import org.store.product.web.domain.Product;

public class JdbcRegistryDao implements RegistryDao {
    private static final String INSERT_BY_ID_SQL = "INSERT INTO products (name, description, price) VALUES (?, ?, ?);";
    private static final String UPDATE_SQL = "UPDATE products SET (name, description, price) VALUES (?, ?, ?);";
    private static final String DELETE_BY_ID_SQL = "DELETE products(name, description, price VALUES (?, ?, ?));";

    private final JdbcTemplate jdbcTemplate;

    public JdbcRegistryDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Product product) {
        return jdbcTemplate.setQuery(product, INSERT_BY_ID_SQL);
    }

    @Override
    public int update(Product product){
        return jdbcTemplate.setQuery(product, UPDATE_SQL);
    }

    @Override
    public int delete(Product product) {
        return jdbcTemplate.setQuery(product, DELETE_BY_ID_SQL);
    }
}
