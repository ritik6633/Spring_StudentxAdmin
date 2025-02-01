package com.example.crud.repository;

import com.example.crud.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin, String> {

    Admin findByEmail(String email);



}
