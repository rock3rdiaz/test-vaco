package com.example.test.controllers;

import com.example.test.entities.Post;
import com.example.test.models.PostModel;
import com.example.test.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService service;

    @GetMapping
    public List<Post> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Optional<Post>> getById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok().body(service.getById(id));
        } catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping
    public ResponseEntity<Post> create(@RequestBody PostModel post) {
        try {
            Post p = service.create(post);
            return ResponseEntity.created(new URI("/posts" + p.getId())).body(p);
        } catch(Exception ex) {
            System.out.println("------------------------------ Error => " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Post> update(@PathVariable("id") Long id, @RequestBody PostModel postModel) {
        try {
            Post p = service.update(id, postModel);
            return ResponseEntity.ok().body(p);
        } catch(Exception ex) {
            System.out.println("------------------------------ Error => " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Post> deleteAll() {
        try {
            service.deleteAll();
            return ResponseEntity.ok().build();
        } catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        try {
            service.deleteById(id);
            return ResponseEntity.ok().build();
        } catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
