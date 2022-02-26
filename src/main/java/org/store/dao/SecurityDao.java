package org.store.dao;

import org.store.web.entity.User;

import java.util.Optional;
import java.util.Set;

public interface SecurityDao {

    Optional<User> findUserById(Long id);

    Set<Long> findUserKeys();

    Optional<User> findUserByName(String username);
}
