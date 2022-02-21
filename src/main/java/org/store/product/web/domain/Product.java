package org.store.product.web.domain;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    int id;
    String name;
    String description;
    double price;
    LocalDateTime date;

}
