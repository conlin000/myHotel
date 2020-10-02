package com.conlin.login;

import lombok.Data;

@Data
public class User {
    private String account;
    private String pwd;
    private int role;
    private String name;
}
