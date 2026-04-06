package com.example.demo.controller;

import com.example.demo.model.Expense;
import com.example.demo.repository.ExpenseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepo;

    // ADD EXPENSE
    @PostMapping
    public Expense addExpense(@RequestBody Expense expense) {
        return expenseRepo.save(expense);
    }

    // GET EXPENSES
    @GetMapping("/{userId}")
    public List<Expense> getExpenses(@PathVariable Long userId) {
        return expenseRepo.findByUserId(userId);
    }

    // RESET ALL EXPENSES
    @DeleteMapping("/reset/{userId}")
    public String resetExpenses(@PathVariable Long userId) {
        List<Expense> list = expenseRepo.findByUserId(userId);
        expenseRepo.deleteAll(list);
        return "All expenses deleted";
    }

    // 🔥 DELETE SINGLE EXPENSE
    @DeleteMapping("/delete/{id}")
    public String deleteExpense(@PathVariable Long id) {
        expenseRepo.deleteById(id);
        return "Expense deleted";
    }
}