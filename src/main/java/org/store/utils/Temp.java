package org.store.utils;

import org.store.products.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Temp {
    public static Map<String, Object> fetch(){
        Product product1 = new Product(1L, "apple", "golden", 10, LocalDate.now());
        Product product2 = new Product(1L, "orange", "orange", 25, LocalDate.now());
        Product product3 = new Product(1L, "banana", "green", 30, LocalDate.now());
        Product product4 = new Product(1L, "tomato", "chery", 50, LocalDate.now());

        java.util.List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        Map<String, Object> model = new HashMap<>();
        model.put("products", products);
        return model;
    }
}
