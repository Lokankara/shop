package org.store.web.utils;

import org.store.web.entity.Product;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class Getters {
    public static Product getProduct(HttpServletRequest request) {
        return Product.builder()
                .id(Long.valueOf(request.getParameter("id")))
                .name(request.getParameter("name"))
                .price(Double.parseDouble(request.getParameter("price")))
                .description(request.getParameter("description"))
                .build();
    }

    public static String getUserToken(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user-token")) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
