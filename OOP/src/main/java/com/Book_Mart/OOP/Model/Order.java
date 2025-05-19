package com.Book_Mart.OOP.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "bookId", nullable = false)
    private Book book;

    private int quantity;
    private double totalPrice;
    private String customerName;
    private String customerEmail;
    private String shippingAddress;
    private String status; // pending, shipped, delivered
    private LocalDateTime orderDate;
    private LocalDateTime updatedDate;
}