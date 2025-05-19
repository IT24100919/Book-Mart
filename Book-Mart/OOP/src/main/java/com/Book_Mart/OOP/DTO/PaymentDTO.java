package com.Book_Mart.OOP.DTO;

import com.Book_Mart.OOP.Model.Book;
import com.Book_Mart.OOP.Model.User;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PaymentDTO {
    private long transactionId;
    private User user;
    private BigDecimal amount;
    private String paymentMethod;
    private String status;
    private LocalDateTime paymentDate;
    private List<Book> transBooks;
}