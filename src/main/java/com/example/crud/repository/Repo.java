package com.example.crud.repository;

import com.example.crud.model.Students;
import org.springframework.data.jpa.repository.JpaRepository;


public interface Repo extends JpaRepository<Students, Integer> {

}

