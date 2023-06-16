package com.paymybuddy.login.service;

import com.paymybuddy.login.model.Transaction;
import com.paymybuddy.login.repository.PaginationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PaginationService {
    @Autowired
    private PaginationRepository paginationRepository;

    public List<Transaction> getAllResults() {
        return paginationRepository.findAll();
    }
}
