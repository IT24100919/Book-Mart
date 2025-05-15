package com.Book_Mart.OOP.Repo;

import com.Book_Mart.OOP.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long>{
    Optional<Admin> findByUsername(String username);
    Optional<Admin> findByEmail(String email);
}