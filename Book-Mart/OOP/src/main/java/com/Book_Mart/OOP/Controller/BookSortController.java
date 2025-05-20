package com.Book_Mart.OOP.Controller;
import com.Book_Mart.OOP.DTO.BookSortDTO;
import com.Book_Mart.OOP.Model.BookSort;
import com.Book_Mart.OOP.Services.BookSortServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v4")
@RequiredArgsConstructor
public class BookSortController {
    private final BookSortServices bookSortService;

    // Create a book
    @PostMapping("/create")
    public ResponseEntity<BookSortDTO> createBook(@RequestBody BookSort book) {
        BookSortDTO bookDTO = bookSortService.saveBook(book);
        return new ResponseEntity<>(bookDTO, HttpStatus.CREATED);
    }

    // Get book by ID
    @GetMapping("/displayOne/{bookId}")
    public ResponseEntity<BookSortDTO> getBook(@PathVariable Long bookId) {
        BookSortDTO bookDTO = bookSortService.getBookById(bookId);
        return ResponseEntity.ok(bookDTO);
    }

    // Get all books
    @GetMapping("/display")
    public ResponseEntity<List<BookSortDTO>> getAllBooks() {
        List<BookSortDTO> books = bookSortService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    // Update a book
    @PutMapping("/update")
    public ResponseEntity<BookSortDTO> updateBook(@PathVariable Long id, @RequestBody BookSort book) {
        book.setId(id);
        BookSortDTO bookDTO = bookSortService.saveBook(book);
        return ResponseEntity.ok(bookDTO);
    }

    // Delete a book
    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long bookId) {
        bookSortService.deleteBook(bookId);
        return ResponseEntity.noContent().build();
    }

    // Sort books by price (ascending)
    @GetMapping("/sort/price")
    public ResponseEntity<List<BookSortDTO>> sortBooksByPrice() {
        List<BookSortDTO> books = bookSortService.sortBooksByPrice();
        return ResponseEntity.ok(books);
    }

    // Sort books by rating (descending)
    @GetMapping("/sort/rating")
    public ResponseEntity<List<BookSortDTO>> sortBooksByRating() {
        List<BookSortDTO> books = bookSortService.sortBooksByRating();
        return ResponseEntity.ok(books);
    }
}