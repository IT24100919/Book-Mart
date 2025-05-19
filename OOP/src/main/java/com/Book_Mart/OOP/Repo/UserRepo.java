package com.Book_Mart.OOP.Repo;

import com.Book_Mart.OOP.Model.Book;
import com.Book_Mart.OOP.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface UserRepo extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE LOWER(u.username) LIKE LOWER(CONCAT('%', :value, '%')) OR LOWER(u.email) LIKE LOWER(CONCAT('%', :value, '%'))")
    List<User> search(@Param("value") String value);

    Optional<Object> findByEmail(String email);
}
