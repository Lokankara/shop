package org.store.dao.jdbc.mapper;

import org.store.web.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ProductMapper {

    private final String[] COLUMN_PRODUCT = {"id", "name", "description", "price", "created"};

    public Optional<Product> productMapper(ResultSet resultSet) throws SQLException {
        return Optional.of(Product.builder()
                .id(resultSet.getLong(COLUMN_PRODUCT[0]))
                .name(resultSet.getString(COLUMN_PRODUCT[1]))
                .description(resultSet.getString(COLUMN_PRODUCT[2]))
                .price(resultSet.getDouble(COLUMN_PRODUCT[3]))
                .created(resultSet.getTimestamp(COLUMN_PRODUCT[4]).toLocalDateTime())
                .build());
    }
}
