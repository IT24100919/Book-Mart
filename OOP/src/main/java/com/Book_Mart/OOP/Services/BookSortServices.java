package com.Book_Mart.OOP.Services;

import com.Book_Mart.OOP.DTO.BookSortDTO;
import com.Book_Mart.OOP.Model.BookSort;
import com.Book_Mart.OOP.Repo.BookSortRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookSortServices {
    private final BookSortRepo bookSortRepo;

    // Create or update a book
    public BookSortDTO saveBook(BookSort book) {
        BookSort savedBook = bookSortRepo.save(book);
        return convertToDTO(savedBook);
    }

    // Get book by ID
    public BookSortDTO getBookById(Long id) {
        BookSort book = bookSortRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        return convertToDTO(book);
    }

    // Get all books
    public List<BookSortDTO> getAllBooks() {
        return bookSortRepo.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Delete book by ID
    public void deleteBook(Long id) {
        if (!bookSortRepo.existsById(id)) {
            throw new RuntimeException("Book not found");
        }
        bookSortRepo.deleteById(id);
    }

    // Sort books by price (ascending) using Quick Sort
    public List<BookSortDTO> sortBooksByPrice() {
        List<BookSort> books = bookSortRepo.findAll();
        quickSort(books, 0, books.size() - 1, Comparator.comparingDouble(BookSort::getPrice));
        return books.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Sort books by rating (descending) using Quick Sort
    public List<BookSortDTO> sortBooksByRating() {
        List<BookSort> books = bookSortRepo.findAll();
        quickSort(books, 0, books.size() - 1, Comparator.comparingDouble(BookSort::getRating).reversed());
        return books.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Quick Sort implementation
    private void quickSort(List<BookSort> books, int low, int high, Comparator<BookSort> comparator) {
        if (low < high) {
            int pi = partition(books, low, high, comparator);
            quickSort(books, low, pi - 1, comparator);
            quickSort(books, pi + 1, high, comparator);
        }
    }

    private int partition(List<BookSort> books, int low, int high, Comparator<BookSort> comparator) {
        BookSort pivot = books.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare(books.get(j), pivot) <= 0) {
                i++;
                BookSort temp = books.get(i);
                books.set(i, books.get(j));
                books.set(j, temp);
            }
        }
        BookSort temp = books.get(i + 1);
        books.set(i + 1, books.get(high));
        books.set(high, temp);
        return i + 1;
    }

    // Convert BookSort entity to BookSortDTO
    private BookSortDTO convertToDTO(BookSort book) {
        BookSortDTO dto = new BookSortDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setPrice(book.getPrice());
        dto.setRating(book.getRating());
        dto.setPublicationDate(String.valueOf(LocalDate.parse(book.getPublicationDate())));
        return dto;
    }
}
