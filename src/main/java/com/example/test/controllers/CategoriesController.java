package com.example.test.controllers;

import com.example.test.entities.Category;
import com.example.test.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories/")
public class CategoriesController {

    @Autowired
    private CategoryService service;

    @GetMapping
    public List<Category> getAllCategories() {
        return service.getAll();
    }
}
