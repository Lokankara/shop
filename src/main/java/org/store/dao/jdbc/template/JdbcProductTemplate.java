package org.store.dao.jdbc.template;

import org.store.dao.jdbc.mapper.ProductMapper;
import org.store.dao.jdbc.utils.ConnectionFactory;
import org.store.exception.ProductNotFoundException;
import org.store.web.entity.Product;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class JdbcProductTemplate {
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
            List<Product> productList = new ArrayList<>();
            while (resultSet.next()) {
                Product product = ROW_PRODUCT_MAPPER.productMapper(resultSet)
                        .orElseThrow(() -> new ProductNotFoundException("Product Not Found"));
                productList.add(product);
            }
            logger.info(String.valueOf(preparedStatement));
            return productList;
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException.getMessage(), sqlException);
        }
    }

    public boolean setProductQuery(Product product, String sql) {
        product.setCreated(LocalDateTime.now());

        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());

            if (product.getId() == 0) {
                preparedStatement.setTimestamp(4, Timestamp.valueOf(product.getCreated()));
            } else {
                preparedStatement.setLong(4, product.getId());
            }
            logger.info(String.valueOf(preparedStatement));
            return preparedStatement.execute();
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    public boolean deleteProductById(Long id, String sql) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            logger.info(String.valueOf(preparedStatement));
            return preparedStatement.execute();
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }
}
