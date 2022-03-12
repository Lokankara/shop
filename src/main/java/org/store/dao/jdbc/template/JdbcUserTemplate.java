package org.store.dao.jdbc.template;

import org.store.dao.jdbc.mapper.UserMapper;
import org.store.dao.jdbc.utils.ConnectionFactory;
import org.store.web.entity.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;
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

    public boolean setUserQuery(User user, String sql) {
        user.setCreated(LocalDateTime.now());
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getSalt());
            preparedStatement.setBoolean(4, true);
            preparedStatement.setBoolean(5, true);
            preparedStatement.setBoolean(6, true);
            preparedStatement.setTimestamp(7, Timestamp.valueOf(user.getCreated()));
            logger.info(String.valueOf(preparedStatement));
            return preparedStatement.execute();
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }
}
