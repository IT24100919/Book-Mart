package com.Book_Mart.OOP.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "Books")
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookId;
    private String bookName;
    private String ISBN;
    private String author;
    private int stock;
    private String details;
    private String manufactureDate;
    private double price;

    //Constructors


//    public Book() {

//    }
//
//    public Book(String bookName, String ISBN, String author, int stock, String details, String manufactureDate, double price) {
//        this.bookName = bookName;
//        this.ISBN = ISBN;
//        this.author = author;
//        this.stock = stock;
//        this.details = details;
//        this.manufactureDate = manufactureDate;
//        this.price = price;
//    }

    //Getters and Setters
//    public long getBookId() {
//        return bookId;
//    }
//
//    public void setBookId(long bookId) {
//        this.bookId = bookId;
//    }
//
//    public String getBookName() {
//        return bookName;
//    }
//
//    public void setBookName(String bookName) {
//        this.bookName = bookName;
//    }
//
//    public String getISBN() {
//        return ISBN;
//    }
//
//    public void setISBN(String ISBN) {
//        this.ISBN = ISBN;
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }
//
//    public int getStock() {
//        return stock;
//    }
//
//    public void setStock(int stock) {
//        this.stock = stock;
//    }
//
//    public String getDetails() {
//        return details;
//    }
//
//    public void setDetails(String details) {
//        this.details = details;
//    }
//
//    public String getManufactureDate() {
//        return manufactureDate;
//    }
//
//    public void setManufactureDate(String manufactureDate) {
//        this.manufactureDate = manufactureDate;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }


}
