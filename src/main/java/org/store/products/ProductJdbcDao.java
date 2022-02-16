package org.store.products;

import org.store.utils.Props;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductJdbcDao extends Props implements ProductDao {

    private static final ProductMapper ROW_MAPPER = new ProductMapper();
    private static final String FIND_ALL_SQL = "SELECT id, name, description, price, date FROM products;";

    @Override
    public List<Product> findAll() {
        return select(FIND_ALL_SQL);
    }

    @Override
    public boolean save(Product product) {
        return false;
    }

    private List<Product> select(String sql) {
        List<Product> productList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Product product = ROW_MAPPER.productMapper(resultSet);
                productList.add(product);
            }
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
            throw new RuntimeException("Failed fetch to DB", sqlException);
        }
        return productList;
    }
}
