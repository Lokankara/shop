package org.store.dao.jdbc;

import org.store.dao.UserDao;
import org.store.dao.jdbc.template.JdbcUserTemplate;
import org.store.web.entity.User;

import java.util.Optional;

public class JdbcUserDao implements UserDao {

    private static final String SELECT_BY_USER_ID_SQL = "SELECT id, name, password, auth, enabled, expired, created FROM users WHERE id=?;";
    private static final String SELECT_BY_USERNAME_SQL = "SELECT id, name, password, salt, auth, enabled, expired, created FROM users WHERE name=?;";
    private static final String INSERT_USER_SQL = "INSERT INTO users (name, password, salt, auth, enabled, expired, created) VALUES (?, ?, ?, ?, ?, ?, ?);";

    private final JdbcUserTemplate jdbcUserTemplate;

    public JdbcUserDao(JdbcUserTemplate jdbcUserTemplate) {
        this.jdbcUserTemplate = jdbcUserTemplate;
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return jdbcUserTemplate.findUserByIdQuery(id, SELECT_BY_USER_ID_SQL);
    }

    @Override
    public Optional<User> findUserByName(String username) {
        return jdbcUserTemplate.findUserByNameQuery(
                username, SELECT_BY_USERNAME_SQL);
    }

    public boolean saveUser(User user) {
        return jdbcUserTemplate
                .setUserQuery(user, INSERT_USER_SQL);
    }
}