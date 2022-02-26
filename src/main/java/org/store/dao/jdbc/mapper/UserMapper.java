package org.store.dao.jdbc.mapper;

import org.store.web.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class UserMapper {

    private final String[] COLUMN_USER = {"id", "username", "password", "auth", "enabled", "expired", "created"};

    public User userMapper(ResultSet resultSet) throws SQLException {

        return User.builder()
                .user_id(resultSet.getLong(COLUMN_USER[0]))
                .username(resultSet.getString(COLUMN_USER[1]))
                .password(resultSet.getString(COLUMN_USER[2]))
                .auth(resultSet.getBoolean(COLUMN_USER[3]))
                .enabled(resultSet.getBoolean(COLUMN_USER[4]))
                .expired(resultSet.getBoolean(COLUMN_USER[5]))
                .created(LocalDateTime.parse(resultSet.getString(COLUMN_USER[6])))
                .build();
    }
}
//private final Object[] USER = new Object[COLUMN_USER.length];
//        for (int i = 0; i < COLUMN_USER.length; i++) {
//       USER[i] = resultSet.getString(COLUMN_USER[i]);
//        USER[i] =resultSet.getBoolean(COLUMN_USER[i]);
//    }