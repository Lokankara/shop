package org.store.secure.registry;

import org.store.product.dao.jdbc.JdbcProductTemplate;
import org.store.user.dao.jdbc.JdbcUserTemplate;
import org.store.user.web.domain.User;

public class JdbcRegistryDao implements RegistryDao {
    private static final String INSERT_USER_SQL = "INSERT INTO users (name, email, password) VALUES (?, ?, ?);";

    private final JdbcProductTemplate jdbcProductTemplate;
    private final JdbcUserTemplate jdbcUserTemplate;

    public JdbcRegistryDao(JdbcProductTemplate jdbcProductTemplate, JdbcUserTemplate jdbcUserTemplate) {
        this.jdbcProductTemplate = jdbcProductTemplate;
        this.jdbcUserTemplate = jdbcUserTemplate;
    }

    @Override
    public int saveUser(User user) {
        return jdbcUserTemplate.setUserQuery(user, INSERT_USER_SQL);
    }
}
