package com.Book_Mart.OOP.DTO;

import lombok.Data;

@Data

public class UserDTO {
    private long userId;
    private String username;
    private String password;
    private String email;
    private String Role;
}
