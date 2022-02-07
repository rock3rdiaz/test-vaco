package com.example.test.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PostModel {

    private Long id;

    private String title;
    private String text;
    private LocalDateTime timestamp;
    private int categoryId;
}
