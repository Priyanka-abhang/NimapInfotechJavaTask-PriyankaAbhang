package com.springrest.springrest.controller;

import com.springrest.springrest.model.Category;
import com.springrest.springrest.repository.CategoryDetailClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryDetailsController {

    @Autowired
    private CategoryDetailClassRepository categoryRepository;

    @GetMapping
    public Page<Category> getAllCategories(@RequestParam(defaultValue = "0") int page) {
        return categoryRepository.findAll(PageRequest.of(page, 5));
    }

    //@PostMapping
    //public Category createCategory(@RequestBody Category category) {
     //   return categoryRepository.save(category);
    //}

    //@PutMapping("/{id}")
    //public Category updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {
     //   return categoryRepository.findById(id).map(category -> {
      //      category.setName(categoryDetails.getName());
      //      return categoryRepository.save(category);
       // }).orElseThrow(() -> new RuntimeException("Category not found"));
    //}
    
    
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        if (category.getProducts() == null) {
            category.setProducts(new ArrayList<>());
        }

        Category savedCategory = categoryRepository.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @GetMapping("/{id}")
    public Optional<Category> getCategoryById(@PathVariable Long id) {
        return categoryRepository.findById(id);
    }
    
    @PutMapping
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
        if (category.getId() == null) {
            return ResponseEntity.badRequest().body(null); 
        }

        Optional<Category> existingCategory = categoryRepository.findById(category.getId());
        if (!existingCategory.isPresent()) {
            return ResponseEntity.notFound().build(); 
        }

        Category updatedCategory = existingCategory.get();
        updatedCategory.setName(category.getName());
        categoryRepository.save(updatedCategory);

        return ResponseEntity.ok(updatedCategory); // Return the updated category
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryRepository.deleteById(id);
    }
}

