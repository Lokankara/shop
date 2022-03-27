package org.store.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.store.exception.UserNotFoundException;
import org.store.web.entity.Session;
import org.store.web.entity.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final UserService userService;

    private final Session session = new Session();
    private Map<String, User> tokens = Collections.synchronizedMap(new HashMap<>());

    private void addEncodedSalt(User user) {
        String salt = generateUUID();
        user.setSalt(salt);
        String encoded = encode(user.getPassword(), salt);
        user.setPassword(encoded);
    }

    private String setToken(User userFromDb) {
        Map<User, String> userTokens = session.getTokenStorage();
        userTokens.put(userFromDb, generateUUID());
        session.setTokenStorage(userTokens);
        session.setExpired(true);
        return session.getToken();
    }

    private String encode(String rawPassword, String salt) {
        return DigestUtils.sha256Hex((rawPassword + salt).getBytes(StandardCharsets.UTF_8));
    }

    private void register(User user) {
        setToken(user);
        addEncodedSalt(user);
        userService.saveUser(user);
    }

    public Session setCookieToken(User user) {
        String token = setToken(user);
        Cookie cookie = new Cookie(user.getRole() + "-token", token);
        cookie.setMaxAge(300);
        session.getUser().setAuth(true);
        session.setToken(cookie.getValue());
        return session;
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public void setSession(Optional<User> user, HttpSession sessionStorage) {
        if (user.isEmpty()) {
            session.setUser(new User());
        } else {
            Optional<User> optionalUser = userService.findUserByName(user.get().getUsername());
            if (optionalUser.isPresent()) {
                session.setUser(optionalUser.get());
                sessionStorage.setAttribute("session", session);
            }
        }
    }

    public void login(User user) {

        Optional<User> optionalUser = userService.findUserByName(user.getUsername());

        if (optionalUser.isPresent()) {
            User userDb = optionalUser.orElseThrow();
            boolean isValid = isExisted(user, userDb);
            if (!isValid) {
                throw new UserNotFoundException("User not valid");
            }
            user.setRole(userDb.getRole());
            setCookieToken(user);
        } else {
            throw new UserNotFoundException("User not founded");
        }
    }

    private boolean isExisted(User user, User userDb) {
        boolean isValidUser = userDb.getUsername().equals(user.getUsername());
        String saltedPassword = encode(user.getPassword(), userDb.getSalt());
        boolean isValidPassword = userDb.getPassword().equals(saltedPassword);
        return isValidUser && isValidPassword;
    }

    public boolean isAuthorization(String token) {
        return tokens.containsKey(token);
    }
}