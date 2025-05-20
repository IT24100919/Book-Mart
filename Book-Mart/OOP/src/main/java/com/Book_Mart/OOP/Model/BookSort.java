package com.Book_Mart.OOP.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "booksSort")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookSort {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private double rating;

    @Column(nullable = false)
    private String publicationDate;

    @Column(nullable = false)
    private int stock;
}