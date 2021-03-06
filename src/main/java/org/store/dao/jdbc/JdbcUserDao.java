package org.store.dao.jdbc;

import org.store.dao.UserDao;
import org.store.dao.jdbc.template.JdbcUserTemplate;
import org.store.web.entity.User;

import java.util.Optional;

public class JdbcUserDao implements UserDao {

    private static final String SELECT_BY_USERNAME_SQL = "SELECT username, password, salt, auth FROM users WHERE username=?;";
    private static final String INSERT_USER_SQL = "INSERT INTO users (username, password, salt, role, auth, created) VALUES (?, ?, ?, ?, ?, ?);";

    private final JdbcUserTemplate jdbcUserTemplate;

    public JdbcUserDao(JdbcUserTemplate jdbcUserTemplate) {
        this.jdbcUserTemplate = jdbcUserTemplate;
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return jdbcUserTemplate.findUserByNameQuery(
                id, SELECT_BY_USERNAME_SQL);
    }

    public boolean saveUser(User user) {
        return jdbcUserTemplate
                .setUserQuery(user, INSERT_USER_SQL);
    }
}