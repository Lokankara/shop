package org.store.user.dao.jdbc;

import org.store.product.dao.jdbc.ConnectionFactory;
import org.store.user.web.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

public class JdbcUserTemplate implements AutoCloseable {
    private final UserMapper ROW_USER_MAPPER = new UserMapper();
    private final ConnectionFactory connectionFactory;

    public JdbcUserTemplate(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    Logger logger = Logger.getLogger(JdbcUserTemplate.class.getName());

    public Map<Long, User> getUserQuery(String sql) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            logger.info(String.valueOf(preparedStatement));
            Map<Long, User> userList = new HashMap<>();
            while (resultSet.next()) {
                User user = ROW_USER_MAPPER.userMapper(resultSet);
                userList.put(user.getId(), user);
            }
            return userList;
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException.getMessage(), sqlException);
        }
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

    public int setUserQuery(User user, String sql) {
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

    public int deleteByIdQuery(Long id, String sql) {
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
