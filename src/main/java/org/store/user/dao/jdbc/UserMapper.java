package org.store.user.dao.jdbc;

import org.store.product.dao.jdbc.ProductMapper;
import org.store.product.web.domain.Product;
import org.store.user.web.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserMapper {

    private final String[] COLUMN_NAMES = {"id", "name", "email", "password", "date"};
    Map<String, Optional<User>> user = new HashMap<>();

    public User userMapper(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getLong(COLUMN_NAMES[0]))
                .name(resultSet.getString(COLUMN_NAMES[1]))
                .email(resultSet.getString(COLUMN_NAMES[2]))
                .password(resultSet.getString(COLUMN_NAMES[3]))
                .created(LocalDateTime.now())
                .build();
    }
}
