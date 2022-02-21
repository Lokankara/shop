package org.store.user.dao.jdbc;

import org.store.user.dao.UserDao;
import org.store.user.web.domain.User;

import java.util.Map;
import java.util.Optional;

public class JdbcUserDao implements UserDao {

    private static final String SELECT_ALL_SQL = "SELECT id, name, email, password,  FROM users LIMIT 100;";
    private static final String SELECT_BY_ID_SQL = "SELECT id, name, description, price, date FROM users WHERE id=?;";
    private static final String UPDATE_SQL = "UPDATE users SET name =?, description =?, price =? WHERE id =?;";
    private static final String DELETE_BY_ID_SQL = "DELETE FROM users WHERE id=?;";

    private final JdbcUserTemplate jdbcUserTemplate;

    public JdbcUserDao(JdbcUserTemplate jdbcUserTemplate) {
        this.jdbcUserTemplate = jdbcUserTemplate;
    }

    @Override
    public Map<Long, User> findAllUsers() {
        return jdbcUserTemplate.getUserQuery(SELECT_ALL_SQL);
    }

    @Override
    public int updateUser(User user) {
        return jdbcUserTemplate.setUserQuery(user, UPDATE_SQL);
    }

    @Override
    public int deleteUserById(Long id) {return jdbcUserTemplate.deleteByIdQuery(id, DELETE_BY_ID_SQL);}

    @Override
    public Optional<User> findUserById(Long id) {
        return Optional.empty();
    }

}
