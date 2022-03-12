package org.store.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long user_id;
    private String username;
    private String password;
    private String salt;
    private boolean auth;
    private boolean enabled;
    private boolean expired;
    private LocalDateTime created;
}