package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;
    private Long userId;

    public Income() {}

    public Long getId() { return id; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}