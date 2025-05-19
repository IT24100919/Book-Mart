package com.Book_Mart.OOP.DTO;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrderDTO {
    private Long orderId;
    private Long bookId;
    private int quantity;
    private double totalPrice;
    private String customerName;
    private String customerEmail;
    private String shippingAddress;
    private String status;
    private LocalDateTime orderDate;
    private LocalDateTime updatedDate;
}