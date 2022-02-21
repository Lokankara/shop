package org.store.product.dao.jdbc;

import org.store.exception.ProductNotFoundException;
import org.store.product.web.domain.Product;
import org.store.user.web.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

public class JdbcProductTemplate implements AutoCloseable {
    private final ProductMapper ROW_PRODUCT_MAPPER = new ProductMapper();
    private final ConnectionFactory connectionFactory;

    public JdbcProductTemplate(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    Logger logger = Logger.getLogger(JdbcProductTemplate.class.getName());

    public Map<Long, Product> getProductQuery(String sql) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            logger.info(String.valueOf(preparedStatement));
            Map<Long, Product> productList = new HashMap<>();
            while (resultSet.next()) {
                Product product = ROW_PRODUCT_MAPPER.productMapper(resultSet);
                productList.put(product.getId(), product);
            }
            return productList;
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException.getMessage(), sqlException);
        }
    }

    public int setProductQuery(Product product, String sql) {
        try {
            Connection connection = connectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            logger.info(String.valueOf(preparedStatement));
            return preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    public Optional<Product> findProductByIdQuery(Long id, String sql) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            preparedStatement.setLong(1, id);
            logger.info(String.valueOf(preparedStatement));
            Product product = ROW_PRODUCT_MAPPER.productMapper(resultSet);
            return Optional.of(product);
        } catch (SQLException sqlException) {
            throw new ProductNotFoundException(sqlException.getMessage(), sqlException);
        }
    }

    protected int setUserQuery(User user, String sql) {
        try {
            Connection connection = connectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            if (user.getId() != 0) {
                preparedStatement.setLong(4, user.getId());
            }
            logger.info(String.valueOf(preparedStatement));
            return preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    public int deleteProductById(Long id, String sql) {
        try {
            Connection connection = connectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    @Override
    public void close() {
    }
}
// TODO java.sql.ResultSet is null
