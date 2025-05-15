package com.Book_Mart.OOP.DTO;
import lombok.Data;

@Data

public class AdminDTO {
    private Long id;
    private String username;
    private String email;
    private String password; // Only used for input (e.g., login, create)
    private String role;
    private boolean active;
}