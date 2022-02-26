package org.store.service;

import lombok.AllArgsConstructor;
import org.store.dao.SecurityDao;
import org.store.web.entity.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
public class SecurityService {
    private final SecurityDao securityDao;

    public Optional<User> findUserById(Long id) {
        return securityDao.findUserById(id);
    }

    public Set<Long> findUserKeys() {
        return securityDao.findUserKeys();
    }

//    public boolean authentication(User user, String token) {
//        return securityDao.checkAuthStatus(user,token);
//    }

    public Optional<User> findUserByName(String username) {
        return securityDao.findUserByName(username);
    }

    public boolean isAuthentication(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        boolean isValid = false;
        for (Cookie cookie : cookies) {
            if ("user-token".equals(cookie.getName())) {
                isValid = true;
            }
            break;
        }
        return isValid;
    }

    public User getUser(HttpServletRequest request) {
        try {
            return User.builder()
                    .user_id(Long.valueOf(request.getParameter("id")))
                    .username(request.getParameter("username"))
                    .password(request.getParameter("password"))
                    .auth(Boolean.parseBoolean(request.getParameter("auth")))
                    .enabled(Boolean.parseBoolean(request.getParameter("enabled")))
                    .expired(Boolean.parseBoolean(request.getParameter("expired")))
                    .created(LocalDateTime.parse(request.getParameter("created")))
                    .build();
        } catch (RuntimeException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }
}
