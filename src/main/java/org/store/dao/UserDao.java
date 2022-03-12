package org.store.dao;

import org.store.web.entity.User;

import java.util.Optional;

public interface UserDao {

    Optional<User> findUserById(Long id);

    Optional<User> findUserByName(String username);
}
