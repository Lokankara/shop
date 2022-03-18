package org.store.service;

import lombok.AllArgsConstructor;
import org.store.dao.UserDao;
import org.store.web.entity.User;

import java.util.Optional;

@AllArgsConstructor
public class UserService {

    private final UserDao userDao;

    public boolean saveUser(User user) {
        return userDao.saveUser(user);
    }

    public Optional<User> findUserById(Long id) {
        return userDao.findUserById(id);
    }

}
