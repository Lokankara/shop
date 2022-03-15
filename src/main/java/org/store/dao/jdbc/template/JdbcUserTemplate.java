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

    public Optional<User> findUserByNameQuery(String username, String sql) {
        Optional<User> user = Optional.empty();
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.info(String.valueOf(preparedStatement));
            while (resultSet.next()) {
                user = ROW_USER_MAPPER.userMapper(resultSet);
            }
            return user;
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException.getMessage(), sqlException);
        }
    }

    public boolean setUserQuery(User user, String sql) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getSalt());
            preparedStatement.setString(4, "guest");
            preparedStatement.setBoolean(5, true);
            preparedStatement.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            logger.info(String.valueOf(preparedStatement));
            return preparedStatement.execute();
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }
}