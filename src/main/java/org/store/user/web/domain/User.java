package org.store.user.web.domain;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    Long id;
    String name;
    String email;
    String password;
    boolean auth;
    boolean enabled;
    boolean locked;
    LocalDateTime created;
}