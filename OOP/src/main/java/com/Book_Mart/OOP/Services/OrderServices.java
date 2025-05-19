package com.Book_Mart.OOP.Services;

import com.Book_Mart.OOP.DTO.OrderDTO;
import com.Book_Mart.OOP.Model.Book;
import com.Book_Mart.OOP.Model.Order;
import com.Book_Mart.OOP.Repo.BookRepo;
import com.Book_Mart.OOP.Repo.OrderRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServices {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private ModelMapper modelMapper;

    public OrderDTO createOrder(OrderDTO orderDTO) {
        // Verify book exists and has enough stock
        Optional<Book> bookOptional = bookRepo.findById(orderDTO.getBookId());
        if (bookOptional.isEmpty()) {
            throw new RuntimeException("Book not found with ID: " + orderDTO.getBookId());
        }

        Book book = bookOptional.get();
        if (book.getStock() < orderDTO.getQuantity()) {
            throw new RuntimeException("Insufficient stock for book: " + book.getBookName());
        }

        // Update book stock
        book.setStock(book.getStock() - orderDTO.getQuantity());
        bookRepo.save(book);

        // Create order
        Order order = modelMapper.map(orderDTO, Order.class);
        order.setBook(book);
        order.setTotalPrice(book.getPrice() * orderDTO.getQuantity());
        order.setStatus("pending");
        order.setOrderDate(LocalDateTime.now());
        order.setUpdatedDate(LocalDateTime.now());

        orderRepo.save(order);
        return modelMapper.map(order, OrderDTO.class);
    }

    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepo.findAll();
        return orders.stream()
                .map(order -> modelMapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());
    }

    public OrderDTO getOrderById(Long orderId) {
        Optional<Order> order = orderRepo.findById(orderId);
        if (order.isPresent()) {
            return modelMapper.map(order.get(), OrderDTO.class);
        } else {
            throw new RuntimeException("Order not found with ID: " + orderId);
        }
    }

    public List<OrderDTO> getOrdersByStatus(String status) {
        List<Order> orders = orderRepo.findByStatus(status);
        return orders.stream()
                .map(order -> modelMapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());
    }

    public List<OrderDTO> getOrdersByCustomer(String email) {
        List<Order> orders = orderRepo.findByCustomerEmail(email);
        return orders.stream()
                .map(order -> modelMapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());
    }

    public OrderDTO updateOrderStatus(Long orderId, String newStatus) {
        Optional<Order> orderOptional = orderRepo.findById(orderId);
        if (orderOptional.isEmpty()) {
            throw new RuntimeException("Order not found with ID: " + orderId);
        }

        Order order = orderOptional.get();
        order.setStatus(newStatus);
        order.setUpdatedDate(LocalDateTime.now());
        orderRepo.save(order);

        return modelMapper.map(order, OrderDTO.class);
    }

    public void cancelOrder(Long orderId) {
        Optional<Order> orderOptional = orderRepo.findById(orderId);
        if (orderOptional.isEmpty()) {
            throw new RuntimeException("Order not found with ID: " + orderId);
        }

        Order order = orderOptional.get();

        // Restore book stock if order was pending
        if ("pending".equals(order.getStatus())) {
            Book book = order.getBook();
            book.setStock(book.getStock() + order.getQuantity());
            bookRepo.save(book);
        }

        orderRepo.deleteById(orderId);
    }
}