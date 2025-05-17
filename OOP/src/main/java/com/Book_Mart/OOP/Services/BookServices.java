package com.Book_Mart.OOP.Services;
import com.Book_Mart.OOP.DTO.BookDTO;
import com.Book_Mart.OOP.Model.Book;
import com.Book_Mart.OOP.Repo.BookRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServices {
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private ModelMapper modelMapper;

    public BookDTO createBook(BookDTO bookDTO){
        bookRepo.save(modelMapper.map(bookDTO, Book.class));
        return bookDTO;
    }

    public List<BookDTO> getAll() {
        List<Book> books = bookRepo.findAll();
        return books.stream().map(book -> modelMapper.map(book, BookDTO.class)).collect(Collectors.toList());
    }

    public BookDTO displayByID(Long bookId){
        Optional<Book> book =bookRepo.findById(bookId);
        if(book.isPresent()){
            Book dBook=book.get();
            return modelMapper.map(dBook, BookDTO.class);
        }else{
            throw new RuntimeException("Can't Find a Book by This ID : "+bookId);
        }
    }

    public void updateBook(BookDTO bookDTO){
        Book book=modelMapper.map(bookDTO, Book.class);
        Optional<Book> cBook = bookRepo.findById(book.getBookId());
        if(cBook.isPresent()){
            Book uBook=cBook.get();
            uBook.setBookName(book.getBookName());
            uBook.setISBN(book.getISBN());
            uBook.setDetails(book.getDetails());
            uBook.setAuthor(book.getAuthor());
            uBook.setPrice(book.getPrice());
            uBook.setStock(book.getStock());
            uBook.setManufactureDate(book.getManufactureDate());
            bookRepo.save(uBook);
        }else {
            throw new RuntimeException("Can't Find a Book by This ID : "+book.getBookId());
        }
    }

    public void deleteBook(Long bookId){
        bookRepo.deleteById(bookId);
    }

    public List<BookDTO> search(String value) {
        List<Book> searchedBooks = bookRepo.search(value);
        return searchedBooks.stream().map(book -> modelMapper.map(book, BookDTO.class)).collect(Collectors.toList());
    }


}