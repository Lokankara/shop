package org.store.web.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Session {
    private String token;
    private boolean expired;
    private User user;
    private List<Long> products;
}
