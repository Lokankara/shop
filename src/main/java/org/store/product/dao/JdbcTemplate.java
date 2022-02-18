package org.store.product.dao;

import org.store.product.web.domain.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
    private static final ProductMapper ROW_MAPPER = new ProductMapper();
    private final ConnectionFactory connectionFactory;

    public JdbcTemplate(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    protected List<Product> getQuery(String sql) {

        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<Product> productList = new ArrayList<>();
            while (resultSet.next()) {
                Product product = ROW_MAPPER.productMapper(resultSet);
                productList.add(product);
            }
            return productList;
        } catch (SQLException sqlException) {
            throw new RuntimeException("Failed fetch to DB "
                    + sqlException.getMessage(), sqlException);
        }
    }

    protected int setQuery(Product product, String sql) {
        try {
            Connection connection = connectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(product.getDate()));
            preparedStatement.executeUpdate();
            return product.getId();

        } catch (SQLException exception) {
            throw new RuntimeException("Failed fetch to DB "
                    + exception.getMessage(), exception);
        }
    }
}
