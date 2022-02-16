package org.store.products;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper {

    protected String[] row = {"id", "name", "description", "price", "date"};

    public Product productMapper(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt(row[0]));
        product.setName(resultSet.getString(row[1]));
        product.setDescription(resultSet.getString(row[2]));
        product.setPrice(resultSet.getInt(row[3]));
        product.setDate(resultSet.getTimestamp(row[4]).toLocalDateTime());
        return product;
    }
}
