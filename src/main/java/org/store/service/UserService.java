package org.store.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.store.dao.UserDao;
import org.store.web.entity.User;

import java.util.Optional;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao =userDao;
    }

    public boolean saveUser(User user) {
        return userDao.saveUser(user);
    }

    public Optional<User> findUserById(Long id) {
        return userDao.findUserById(id);
    }

    public Optional<User> findUserByName(String username) { return  userDao.findByUserName(username);
    }

}
