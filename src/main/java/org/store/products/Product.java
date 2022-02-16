package org.store.products;

import java.time.LocalDateTime;

public class Product {

    int id;
    String name;
    String description;
    int price;
    LocalDateTime date;

    public Product() {}

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) { this.date = date; }

    public Product(int id, String name, String description, int price, LocalDateTime date) {
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
