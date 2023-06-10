package com.example.demo.controlers.resources;

import com.example.demo.entities.User;
import jakarta.annotation.Resource;
import lombok.Data;

import java.util.Date;

@Resource
@Data
public class FineRes {
    private Long id;
    private double amount;
    private Date dueDate;
    private boolean isPaid;
    private UserRes user;

    public boolean getIsPaid() {
        return isPaid;
    }
}
