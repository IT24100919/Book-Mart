package com.Book_Mart.OOP.Controller;

import com.Book_Mart.OOP.DTO.OrderDTO;
import com.Book_Mart.OOP.Services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v6")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderServices orderServices;

    @PostMapping("/create")
    public OrderDTO createOrder(@RequestBody OrderDTO orderDTO) {
        return orderServices.createOrder(orderDTO);
    }

    @GetMapping("/display")
    public List<OrderDTO> getAllOrders() {
        return orderServices.getAllOrders();
    }

    @GetMapping("/display/{orderId}")
    public OrderDTO getOrderById(@PathVariable Long orderId) {
        return orderServices.getOrderById(orderId);
    }

    @GetMapping("/status/{status}")
    public List<OrderDTO> getOrdersByStatus(@PathVariable String status) {
        return orderServices.getOrdersByStatus(status);
    }

    @GetMapping("/customer/{email}")
    public List<OrderDTO> getOrdersByCustomer(@PathVariable String email) {
        return orderServices.getOrdersByCustomer(email);
    }

    @PutMapping("/update/{orderId}/{status}")
    public OrderDTO updateOrderStatus(
            @PathVariable Long orderId,
            @PathVariable String status) {
        return orderServices.updateOrderStatus(orderId, status);
    }

    @DeleteMapping("/delete/{orderId}")
    public void cancelOrder(@PathVariable Long orderId) {
        orderServices.cancelOrder(orderId);
    }
}