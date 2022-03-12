package org.store.service;

import org.store.dao.UserDao;
import org.store.web.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Optional<User> findUserById(Long id) {
        return userDao.findUserById(id);
    }

    public Optional<User> findUserByName(String username) {
        return userDao.findUserByName(username);
    }

    public Optional<User> getUser(HttpServletRequest request) {
        try {
            return Optional.of(User.builder()
                    .user_id(Long.valueOf(request.getParameter("id")))
                    .username(request.getParameter("username"))
                    .password(request.getParameter("password"))
                    .auth(Boolean.parseBoolean(request.getParameter("auth")))
                    .enabled(Boolean.parseBoolean(request.getParameter("enabled")))
                    .expired(Boolean.parseBoolean(request.getParameter("expired")))
                    .created(LocalDateTime.parse(request.getParameter("created")))
                    .build());
        } catch (RuntimeException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }
}
