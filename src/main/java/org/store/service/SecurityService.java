package org.store.service;

import lombok.AllArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.store.web.entity.Session;
import org.store.web.entity.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class SecurityService {
    private final UserService userService;
    private final Session session;


    private void addEncodedSalt(User user) {
        String salt = generateUUID();
        user.setSalt(salt);
        String encoded = encode(user.getPassword(), salt);
        user.setPassword(encoded);
    }

    private String setToken(Long id) {
        User userFromDb = userService.findUserById(id).orElseThrow();
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
        setToken(user.getUser_id());
        addEncodedSalt(user);
        userService.saveUser(user);
    }

    public Session checkUserToken(Session session, Cookie[] cookies) {
        User user = session.getUser();
        if (user == null) {
            user = new User();
            session.setUser(user);
            return session;
        }
        Optional<User> optionalUser = userService.findUserByName(user.getUsername());
        if (optionalUser.isPresent()) {
            session.setUser(optionalUser.orElseThrow());
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user-token")) {
                session.setToken((cookie.getValue()));
            }
            cookie = new Cookie("user-token", generateUUID());
            cookie.setMaxAge(300);
            session.getUser().setAuth(true);
            session.setToken(cookie.getValue());
        }
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
}