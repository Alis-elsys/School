package com.example.demo.controlers.resources;

import com.example.demo.entities.Book;
import com.example.demo.entities.User;
import jakarta.annotation.Resource;
import lombok.Data;

import java.util.Date;

@Resource
@Data
public class TransactionRes {
    private Long id;
    private Date borrowDate;
    private Date returnDate;
    private boolean isOverdue;
    private UserRes user;
    private BookRes book;
}
