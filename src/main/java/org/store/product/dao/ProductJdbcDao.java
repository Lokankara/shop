package org.store.product.dao;

import org.store.product.web.domain.Product;

import java.util.List;

public class ProductJdbcDao implements ProductDao {

    private static final String FIND_ALL_SQL = "SELECT id, name, description, price, date FROM products LIMIT 100;";
    private static final String INSERT_BY_ID_SQL = "INSERT INTO products(name, description, price VALUES (?, ?, ?));";
    private static final String UPDATE_SQL = "UPDATE INTO products(name, description, price VALUES (?, ?, ?));";

    private final JdbcTemplate jdbcTemplate;

    public ProductJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.getQuery(FIND_ALL_SQL);
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
    public int delete(Product product) { return jdbcTemplate.setQuery(product, INSERT_BY_ID_SQL); }
}
