package org.store.dao.jdbc.mapper;

import org.store.web.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserMapper {

    private final String[] COLUMN_USER = {"user_id", "username", "password", "salt", "auth", "enabled", "expired", "created"};

    public Optional<User> userMapper(ResultSet resultSet) throws SQLException {

        return Optional.of(User.builder()
                .username(resultSet.getString(COLUMN_USER[1]))
                .password(resultSet.getString(COLUMN_USER[2]))
                .salt(resultSet.getString(COLUMN_USER[3]))
                .auth(resultSet.getBoolean(COLUMN_USER[4]))
                .build());
    }
}