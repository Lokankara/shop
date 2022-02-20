package org.store.product.dao.jdbc;

import org.store.product.web.domain.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate implements AutoCloseable{
    private final ProductMapper ROW_MAPPER = new ProductMapper();
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
            throw new RuntimeException(sqlException.getMessage(), sqlException);
        }
    }
    protected int setQuery(Product product, String sql) {

        try {
            Connection connection = connectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            return preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    protected int setQueryById(int id, String sql) {

        try {
            Connection connection = connectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    @Override
    public void close() {}
}
    // TODO java.sql.ResultSet is null
