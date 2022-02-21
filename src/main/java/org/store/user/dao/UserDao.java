package org.store.user.dao;

import org.store.user.web.domain.User;

import java.util.Map;
import java.util.Optional;

public interface UserDao {

    Map<Long, User> findAllUsers();

    int updateUser(User user);

    int deleteUserById(Long id);

    Optional<User> findUserById(Long id);
}
