package com.example.demo.controller;

import com.example.demo.model.Income;
import com.example.demo.repository.IncomeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/income")
public class IncomeController {

    @Autowired
    private IncomeRepository incomeRepo;

    // ADD INCOME
    @PostMapping
    public Income addIncome(@RequestBody Income income) {
        return incomeRepo.save(income);
    }

    // GET INCOME
    @GetMapping("/{userId}")
    public List<Income> getIncome(@PathVariable Long userId) {
        return incomeRepo.findByUserId(userId);
    }

    // 🔥 RESET ALL INCOME
    @DeleteMapping("/reset/{userId}")
    public String resetIncome(@PathVariable Long userId) {
        List<Income> list = incomeRepo.findByUserId(userId);
        incomeRepo.deleteAll(list);
        return "All income deleted";
    }
}