package com.example.demo.controlers.resources;

import com.example.demo.entities.Author;
import com.example.demo.entities.Publisher;
import com.example.demo.entities.User;
import jakarta.annotation.Resource;
import lombok.Data;

@Resource
@Data
public class BookRes {
    private Long id;
    private String title;
    private AuthorRes author;
    private String genre;
    private PublisherRes publisher;
    private int year;
    private boolean isAvailable;
    private UserRes borrower;

    public boolean getAvailable() {
        return isAvailable;
    }
}
