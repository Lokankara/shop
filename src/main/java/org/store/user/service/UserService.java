package org.store.user.service;

import org.store.exception.UserNotFoundException;
import org.store.user.dao.UserDao;
import org.store.user.web.domain.User;

import java.util.*;
import java.util.Optional;

public class UserService {

        private final UserDao UserDao;

        public UserService(UserDao UserDao) {
            this.UserDao = UserDao;
        }

        public Map<Long, User> findAllUsers() {
            return UserDao.findAllUsers();
        }

        public int updateUser(User user) {
            return UserDao.updateUser(user);
        }

        public int deleteById(Long id) {
            return UserDao.deleteUserById(id);
        }

        public User findUserById(Long id) {
            Optional<User> optionalUser = UserDao.findUserById(id);
            return optionalUser.orElseThrow(() -> new UserNotFoundException("findUserById"));
        }
    }
