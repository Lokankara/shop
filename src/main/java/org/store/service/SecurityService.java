package org.store.service;

import lombok.AllArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.store.web.entity.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

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
        String encoded = encode(salt + user.getPassword());
        user.setPassword(encoded);
        user.setSalt(salt);
    }

    String encode(String value) {
        return DigestUtils.sha256Hex(value.getBytes(StandardCharsets.UTF_8));
    }

    String generateUUID() {
        return UUID.randomUUID().toString();
    }

    String generateToken() {
        String token = generateUUID();
        tokens.add(token);
        return token;
    }

    public String encode(String rawPassword, String salt) {
        return DigestUtils.sha256Hex(rawPassword + salt);
    }

    public boolean isAuthorization(User user, String token) {
        User userDb = userService.findUserById(user.getUser_id()).orElseThrow();
        String salt = userDb.getSalt();
        String hashedSatled = userDb.getPassword();
        return false;
    }
}