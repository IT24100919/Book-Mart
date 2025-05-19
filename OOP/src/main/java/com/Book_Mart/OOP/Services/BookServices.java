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
    private final BookRepo bookRepo;
    @Autowired
    private final ModelMapper modelMapper;

    public BookServices(BookRepo bookRepo, ModelMapper modelMapper) {
        this.bookRepo = bookRepo;
        this.modelMapper = modelMapper;
    }

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

    // QuickSort by price
    public void quickSortByPrice(List<Book> books, int low, int high) {
        if (low < high) {
            int pi = partitionPrice(books, low, high);
            quickSortByPrice(books, low, pi - 1);
            quickSortByPrice(books, pi + 1, high);
        }
    }
    private int partitionPrice(List<Book> books, int low, int high) {
        double pivot = books.get(high).getPrice();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (books.get(j).getPrice() <= pivot) {
                i++;
                swap(books, i, j);
            }
        }
        swap(books, i + 1, high);
        return i + 1;
    }

    private void swap(List<Book> books, int i, int j) {
        Book temp = books.get(i);
        books.set(i, books.get(j));
        books.set(j, temp);
    }

    // New method: get all sorted by price ascending
    public List<BookDTO> getAllSortedByPrice() {
        List<Book> books = bookRepo.findAll();
        quickSortByPrice(books, 0, books.size() - 1);
        return books.stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }


}