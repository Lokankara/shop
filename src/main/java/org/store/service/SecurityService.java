package org.store.service;

import org.store.web.entity.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class SecurityService {

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

    public boolean isAuthorization(User user, String token) {
        return false;
    }
}
