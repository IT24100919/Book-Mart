package com.Book_Mart.OOP.Controller;

import com.Book_Mart.OOP.DTO.BookDTO;
import com.Book_Mart.OOP.Model.Book;
import com.Book_Mart.OOP.Model.Ebook;
import com.Book_Mart.OOP.Services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/display/sort/price")
    public List<BookDTO> displaySortedByPrice() {
        return bookServices.getAllSortedByPrice();
    }

    @GetMapping("/demo/inheritance")
    public String inheritanceDemo() {
        Book book = new Book();
        book.setBookId(1);
        book.setBookName("Example");
        book.setAuthor("Jalitha");
        book.setPrice(150.0);

        Ebook ebook = new Ebook(2, "Harry Potter", "JK Rowling", 200.0, "http://example.com/download");

        // Polymorphism: using same method name
        return "Book: " + book.printDetails() + "<br>Ebook: " + ebook.printDetails();
    }


}