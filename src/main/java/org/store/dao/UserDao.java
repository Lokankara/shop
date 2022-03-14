package org.store.dao;

import org.store.web.entity.User;

import java.util.Optional;

public interface UserDao {

    Optional<User> findUserByName(String username);

    boolean saveUser(User user);
}
