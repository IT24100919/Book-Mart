package com.Book_Mart.OOP.Repo;

import com.Book_Mart.OOP.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE LOWER(b.bookName) LIKE LOWER(CONCAT('%', :value, '%'))")
    List<Book> search(@Param("value") String value);
}

