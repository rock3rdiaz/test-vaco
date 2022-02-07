package com.example.test.services;

import com.example.test.entities.Category;
import com.example.test.repositories.CategoryRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> getAll() {
        return repository.findAll();
    }
}
