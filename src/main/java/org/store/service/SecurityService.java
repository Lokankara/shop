package org.store.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.store.web.entity.Session;
import org.store.web.entity.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;

import static org.store.web.utils.WebUtils.userMapper;

public class SecurityService {
    private final UserService userService;
    private final Session session;

    public SecurityService(UserService userService, Session session) {
        this.userService = userService;
        this.session = session;
    }

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

    public void setUserToken(HttpServletRequest request, HttpServletResponse response, Session session) {

        User user = userMapper(request).orElseThrow();
        session.setUser(user);

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user-token")) {
                session.setToken((cookie.getValue()));
            } else {
                cookie = new Cookie("user-token", generateUUID());
                cookie.setMaxAge(300);
                session.getUser().setAuth(true);
                session.setToken(cookie.getValue());
                response.addCookie(cookie);
            }
        }
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public void checkUser(HttpServletRequest request) {
        HttpSession sessionStorage = request.getSession();

        request.getParameter("");
    }
}