package org.store.product.web.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Product {

    int id;
    String name;
    String description;
    double price;
    LocalDateTime date;

    public Product() {}

    public Product(int id, String name, String description, double price, LocalDateTime date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}
