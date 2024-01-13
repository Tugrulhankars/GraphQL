package com.metropol.grapql.model;

import lombok.Data;

@Data
public class UserRequest {
    private Long id;
    private String userName;
    private String mail;
    private Role role;
}
