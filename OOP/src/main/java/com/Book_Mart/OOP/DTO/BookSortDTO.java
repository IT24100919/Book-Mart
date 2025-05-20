package com.Book_Mart.OOP.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookSortDTO {
    private Long id;
    private String title;
    private String author;
    private double price;
    private double rating;
    private int stock;
    private String publicationDate;
}