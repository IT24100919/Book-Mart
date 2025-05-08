package com.Book_Mart.OOP.DTO;

import lombok.Data;

@Data
public class BookDTO {
    private long bookId;
    private String bookName;
    private String ISBN;
    private String author;
    private int stock;
    private String details;
    private String manufactureDate;
    private double price;
}
