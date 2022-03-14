package org.store.service;

import lombok.AllArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.store.web.entity.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.*;

@AllArgsConstructor
public class SecurityService {

    private final UserService userService;

    private final List<String> tokens = Collections.synchronizedList(new ArrayList<>());

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

    public void addSalt(User user) {
        String salt = generateUUID();
        user.setSalt(salt);
        String encoded = encode(user.getPassword(), salt);
        user.setPassword(encoded);
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    String generateToken() {
        String token = generateUUID();
        tokens.add(token);
        return token;
    }

    public String encode(String rawPassword, String salt) {
        return DigestUtils.sha256Hex((rawPassword + salt).getBytes(StandardCharsets.UTF_8));
    }

    public boolean isAuthorization(User user) {
        User userDb = userService.findUserByName(user.getUsername()).orElseThrow();
        String salt = userDb.getSalt();
        String hashedSalted = userDb.getPassword();
        return false;
    }

    public void checkUser(User user) {
        String username = user.getUsername();
        Optional<User> optionalUserFromDb = userService.findUserByName(username);

        if (optionalUserFromDb.isEmpty()) {
            userService.saveUser(user);
            addSalt(user);
            user.setToken(generateToken());
        } else {
            boolean auth = isAuthorization(user);
            user.setAuth(auth);
        }
    }
}