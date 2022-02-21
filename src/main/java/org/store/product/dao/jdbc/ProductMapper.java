package org.store.product.dao.jdbc;

import org.store.product.web.domain.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ProductMapper {

    private final String[] COLUMN_NAMES = {"id", "name", "description", "price", "date"};

    public Product productMapper(ResultSet resultSet) throws SQLException {
        return Product.builder()
                .id(resultSet.getInt(COLUMN_NAMES[0]))
                .name(resultSet.getString(COLUMN_NAMES[1]))
                .description(resultSet.getString(COLUMN_NAMES[2]))
                .price(resultSet.getDouble(COLUMN_NAMES[3]))
                .date(LocalDateTime.now())
                .build();
    }
}
