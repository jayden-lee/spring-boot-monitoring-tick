package com.jayden.monitoring.user;

import com.jayden.monitoring.db.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {

    private String name;

    public User toEntity() {
        return User.builder()
            .name(name)
            .build();
    }
}
