package org.store.dao.jdbc.template;

import org.store.dao.jdbc.ConnectionFactory;
import org.store.dao.jdbc.mapper.UserMapper;
import org.store.web.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;

public class JdbcUserTemplate {
    private final UserMapper ROW_USER_MAPPER = new UserMapper();
    private final ConnectionFactory connectionFactory;
    private final Logger logger = Logger.getLogger(JdbcUserTemplate.class.getName());

    public JdbcUserTemplate(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public Optional<User> findUserByIdQuery(Long id, String sql) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            preparedStatement.setLong(1, id);
            logger.info(String.valueOf(preparedStatement));
            User user = ROW_USER_MAPPER.userMapper(resultSet);
            return Optional.of(user);
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
                User user = ROW_USER_MAPPER.userMapper(resultSet);
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
            User user = ROW_USER_MAPPER.userMapper(resultSet);
            return Optional.of(user);
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException.getMessage(), sqlException);
        }
    }

    public Map<Long, User> findAllUsers(String sql) {
        return new HashMap<>();
    }
}
