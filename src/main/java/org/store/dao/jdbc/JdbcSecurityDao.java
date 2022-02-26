package org.store.dao.jdbc;

import org.store.dao.SecurityDao;
import org.store.dao.jdbc.template.JdbcUserTemplate;
import org.store.web.entity.User;

import java.util.Optional;
import java.util.Set;

public class JdbcSecurityDao implements SecurityDao {

    private static final String SELECT_BY_ID_SQL = "SELECT id, name, password, auth, enabled, expired, created FROM users WHERE id=?;";
    private static final String SELECT_BY_USERNAME_SQL = "SELECT id, name, password, auth, enabled, expired, created FROM users WHERE name=?;";
    private static final String SELECT_USER_IDS_SQL = "SELECT id FROM users;";

    private final JdbcUserTemplate jdbcUserTemplate;

    public JdbcSecurityDao(JdbcUserTemplate jdbcUserTemplate) {
        this.jdbcUserTemplate = jdbcUserTemplate;
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return jdbcUserTemplate.findUserByIdQuery(id, SELECT_BY_ID_SQL);
    }

    @Override
    public Set<Long> findUserKeys() {
        return jdbcUserTemplate.findAllKeys(SELECT_USER_IDS_SQL);
    }

    @Override
    public Optional<User> findUserByName(String username) {
        return jdbcUserTemplate.findUserByNameQuery(
                username, SELECT_BY_USERNAME_SQL);
    }
}
