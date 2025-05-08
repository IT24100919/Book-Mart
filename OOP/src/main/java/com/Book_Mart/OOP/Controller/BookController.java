package com.Book_Mart.OOP.Controller;

import com.Book_Mart.OOP.DTO.BookDTO;
import com.Book_Mart.OOP.Model.Book;
import com.Book_Mart.OOP.Services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class BookController {
    @Autowired

    private BookServices bookServices;

    @PostMapping("/create")
    public BookDTO create(@RequestBody BookDTO bookDTO){
        return bookServices.createBook(bookDTO);
    }

    @GetMapping("/display")
    public List<BookDTO> display(){
        return bookServices.getAll();
    }

    @GetMapping("/displayOne/{bookId}")
    public BookDTO displayOne(@PathVariable Long bookId){
        return bookServices.displayByID(bookId);
    }

    @PutMapping("/update")
    public void update(@RequestBody BookDTO bookDTO){
        bookServices.updateBook(bookDTO);
    }

    @DeleteMapping("/delete/{bookId}")
    public void delete(@PathVariable Long bookId){
        bookServices.deleteBook(bookId);
    }

    @GetMapping("/search/{value}")
    public List<BookDTO> search(@PathVariable String value){
        return bookServices.search(value);
    }

}
