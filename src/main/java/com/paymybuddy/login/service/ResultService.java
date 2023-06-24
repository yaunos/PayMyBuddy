package com.paymybuddy.login.service;

import com.paymybuddy.login.model.Transaction;
import com.paymybuddy.login.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {
    @Autowired
    private ResultRepository resultRepository;

    public List<Transaction> getAllResults() {
        return resultRepository.findAll();
    }
}
