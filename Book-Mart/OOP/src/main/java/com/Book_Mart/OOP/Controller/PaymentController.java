//package com.Book_Mart.OOP.Controller;
//
//import com.Book_Mart.OOP.DTO.PaymentDTO;
//import com.Book_Mart.OOP.Services.PaymentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/v5")
//@CrossOrigin
//public class PaymentController {
//    @Autowired
//    private PaymentService paymentServices;
//
//    @PostMapping("/createPayment")
//    public PaymentDTO create(@RequestBody PaymentDTO paymentDTO) {
//        return paymentServices.createPayment(paymentDTO);
//    }
//
//    @GetMapping("/displayPayments")
//    public List<PaymentDTO> display() {
//        return paymentServices.getAll();
//    }
//
//    @GetMapping("/displayOnePayment/{paymentId}")
//    public PaymentDTO displayOne(@PathVariable long paymentId) {
//        return paymentServices.displayByID(paymentId);
//    }
//
//    @PutMapping("/updatePayment")
//    public void update(@RequestBody PaymentDTO paymentDTO) {
//        paymentServices.updatePayment(paymentDTO);
//    }
//
//    @DeleteMapping("/deletePayment/{paymentId}")
//    public void delete(@PathVariable long paymentId) {
//        paymentServices.deletePayment(paymentId);
//
//    }
//
//    @GetMapping("/searchPayments/{value}")
//    public List<PaymentDTO> search(@PathVariable String value) {
//        return paymentServices.search(value);
//    }
//}
package com.Book_Mart.OOP.Controller;

import com.Book_Mart.OOP.DTO.PaymentDTO;
import com.Book_Mart.OOP.Services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public void trnsCreate(PaymentDTO transaction){
        paymentService.trnsCreate(transaction);
    }

    @GetMapping("/getAll")
    public List<PaymentDTO> getAllTrns(){
        return paymentService.getAll();
    }

    @GetMapping("/getById/{id}")
    public PaymentDTO getById(@PathVariable long id){
        return paymentService.getById(id);
    }


    @PutMapping("/update")
    public void update(@RequestBody PaymentDTO transaction){
        paymentService.update(transaction);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id){
        paymentService.deleteById(id);
    }


}