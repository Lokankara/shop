package org.store.web.utils;

import org.store.web.entity.Product;

import javax.servlet.http.HttpServletRequest;

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
}
