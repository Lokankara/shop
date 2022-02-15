package org.store.products;

import java.time.LocalDate;

public class Product {

    Long id;
    String name;
    String description;
    int price;
    LocalDate created_at;

    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }

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

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) { this.created_at = created_at; }

    public Product(Long id, String name, String description, int price, LocalDate created_at) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", created=" + created_at +
                '}';
    }
}
