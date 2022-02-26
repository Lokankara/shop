package org.store.dao.jdbc.template;

import org.store.dao.jdbc.ConnectionFactory;
import org.store.dao.jdbc.mapper.ProductMapper;
import org.store.exception.ProductNotFoundException;
import org.store.web.entity.Product;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Logger;

public class JdbcProductTemplate implements AutoCloseable {
    private final ProductMapper ROW_PRODUCT_MAPPER = new ProductMapper();
    private final ConnectionFactory connectionFactory;

    public JdbcProductTemplate(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    Logger logger = Logger.getLogger(JdbcProductTemplate.class.getName());

    public List<Product> getProductsQuery(String sql) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            logger.info(String.valueOf(preparedStatement));
            List<Product> productList = new ArrayList<>();
            while (resultSet.next()) {
                Product product = ROW_PRODUCT_MAPPER.productMapper(resultSet);
                productList.add(product);
            }
            return productList;
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException.getMessage(), sqlException);
        }
    }
    public int setProductQuery(Product product, String sql) {
        if (product.getCreated() == null){product.setCreated(LocalDateTime.now());}
        try {
            Connection connection = connectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(product.getCreated()));
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
//        connection.close();
    }
}
