package com.example.test.services;

import com.example.test.entities.Category;
import com.example.test.entities.Post;
import com.example.test.models.PostModel;
import com.example.test.repositories.CategoryRepository;
import com.example.test.repositories.PostRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class PostService {

    @Autowired
    private PostRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Post> getAll() {
        return repository.findAll(Sort.by("Timestamp").descending());
    }

    public Optional<Post> getById(Long id) {
        return repository.findById(id);
    }

    public Post create(PostModel postModel) {
        try {
            Optional<Category> category = categoryRepository.findById(Long.valueOf(postModel.getCategoryId()));
            Post post = new Post();
            post.setTimestamp(LocalDateTime.now());
            post.setTitle(postModel.getTitle());
            post.setText(postModel.getText());
            post.setCategory(category.get());
            return repository.save(post);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Post update(Long id, PostModel postModel) {
        try {
            Post currentPostVersion = repository.getById(id);
            currentPostVersion.setTitle(postModel.getTitle());
            currentPostVersion.setText(postModel.getText());
            Optional<Category> category = categoryRepository.findById(Long.valueOf(postModel.getCategoryId()));
            currentPostVersion.setCategory(category.get());
            return repository.saveAndFlush(currentPostVersion);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
