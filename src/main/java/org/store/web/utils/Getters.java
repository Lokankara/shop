package org.store.web.utils;

import org.store.web.entity.Product;
import org.store.web.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class Getters {
    public static Product productMapper(HttpServletRequest request) {

        Long id = Long.valueOf(request.getParameter("id"));
        String name = (request.getParameter("name"));
        double price = (Double.parseDouble(request.getParameter("price")));
        String description = (request.getParameter("description"));

        return Product.builder()
                .id(id)
                .name(name)
                .price(price)
                .description(description)
                .build();
    }

    public static Optional<User> userMapper(HttpServletRequest request) {
        try {
            return Optional.of(User.builder()
                    .username(request.getParameter("username"))
                    .password(request.getParameter("password"))
                    .build());
        } catch (RuntimeException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }
}
