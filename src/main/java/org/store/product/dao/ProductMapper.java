package org.store.product.dao;

import org.store.product.web.domain.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper {

    private static final String[] COLUMN_NAMES = {"id", "name", "description", "price", "date"};

    public Product productMapper(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt(COLUMN_NAMES[0]));
        product.setName(resultSet.getString(COLUMN_NAMES[1]));
        product.setDescription(resultSet.getString(COLUMN_NAMES[2]));
        product.setPrice(resultSet.getInt(COLUMN_NAMES[3]));
        product.setDate(resultSet.getTimestamp(COLUMN_NAMES[4]).toLocalDateTime());
        return product;
    }
}
