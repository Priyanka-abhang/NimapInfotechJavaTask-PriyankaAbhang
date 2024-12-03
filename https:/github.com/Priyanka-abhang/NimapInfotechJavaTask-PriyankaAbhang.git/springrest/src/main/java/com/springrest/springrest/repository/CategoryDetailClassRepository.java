package com.springrest.springrest.repository;


import com.springrest.springrest.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDetailClassRepository extends JpaRepository<Category, Long> {
}
