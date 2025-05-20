package com.Book_Mart.OOP.Repo;

import com.Book_Mart.OOP.Model.BookSort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookSortRepo extends JpaRepository<BookSort, Long> {
}