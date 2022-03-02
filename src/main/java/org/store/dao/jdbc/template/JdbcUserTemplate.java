package org.store.dao.jdbc.template;

import org.store.dao.jdbc.mapper.UserMapper;
import org.store.web.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;

public class JdbcUserTemplate {
    private final ConnectionFactory connectionFactory;
    private final UserMapper ROW_USER_MAPPER = new UserMapper();

    public JdbcUserTemplate(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    private final Logger logger = Logger.getLogger(JdbcUserTemplate.class.getName());


    public Optional<User> findUserByIdQuery(Long id, String sql) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            preparedStatement.setLong(1, id);
            logger.info(String.valueOf(preparedStatement));
            return ROW_USER_MAPPER.userMapper(resultSet);
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException.getMessage(), sqlException);
        }
    }

    public Set<Long> findAllKeys(String sql) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            logger.info(String.valueOf(preparedStatement));
            Set<Long> productList = new HashSet<>();
            while (resultSet.next()) {
                User user = ROW_USER_MAPPER
                        .userMapper(resultSet).orElseThrow();
                productList.add(user.getUser_id());
            }
            return productList;
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException.getMessage(), sqlException);
        }
    }

    public Optional<User> findUserByNameQuery(String username, String sql) {

        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            preparedStatement.setString(1, username);
            logger.info(String.valueOf(preparedStatement));
            return ROW_USER_MAPPER.userMapper(resultSet);
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException.getMessage(), sqlException);
        }
    }
}
