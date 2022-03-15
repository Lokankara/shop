package org.store.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.store.web.entity.Session;
import org.store.web.entity.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.store.web.utils.Getters.userMapper;

public class SecurityService {
    private final UserService userService;
    private final Session session;
    private Map<User, String> tokens;

    public SecurityService(UserService userService, Session session) {
        this.userService = userService;
        this.session = session;
    }

    public Session isAuthentication(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user-token")) {
                session.setToken(cookie.getValue());
            } else {
                Cookie newCookie = new Cookie("user-token", generateUUID());
                newCookie.setMaxAge(600);
                response.addCookie(newCookie);
                session.setToken(newCookie.getValue());
            }
        }
        return session;
    }

    private void addSalt(User user) {
        String salt = generateUUID();
        user.setSalt(salt);
        String encoded = encode(user.getPassword(), salt);
        user.setPassword(encoded);
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    private void generateToken(User user) {
        tokens.put(user, generateUUID());
    }

    private String encode(String rawPassword, String salt) {
        return DigestUtils.sha256Hex((rawPassword + salt).getBytes(StandardCharsets.UTF_8));
    }

    public boolean isAuthorization(String token) {
        return tokens.containsKey(token);
    }

    public void checkUser(HttpServletRequest request) {

        User user = userMapper(request).orElseThrow();
        String username = user.getUsername();
        Optional<User> optionalUserFromDb = userService.findUserByName(username);
        boolean isValid = false;
        if (!optionalUserFromDb.isEmpty()) {
            isValid = checkNameAndPassword(user, optionalUserFromDb);
        } else {
//            register(user);
        }
        if (isValid) {
            session.setUser(user);
        }
//            generateToken(user);
    }

    private void register(User user) {
        addSalt(user);
        userService.saveUser(user);
    }

    private boolean checkNameAndPassword(User user, Optional<User> userDb) {
        User userFromDb = userDb.orElseThrow();
        String salt = userFromDb.getSalt();
        String rawPassword = user.getPassword();
        String saltedPassword = encode(rawPassword, salt);
        if (saltedPassword.equals(userFromDb.getPassword())) {
            user.setAuth(true);
        }
        return user.isAuth();
    }
}