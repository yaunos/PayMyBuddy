package com.paymybuddy.login.controller;

import com.paymybuddy.login.model.Transaction;
import com.paymybuddy.login.service.ResultService;
import com.paymybuddy.login.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class ResultController {

    // ******* Partie pagination *******
@Autowired
    ResultService resultService;

    @GetMapping("/results")
    public String getResults(Model model, @RequestParam(defaultValue = "1") int currentPage) {
        List<Transaction> transactionList = resultService.getAllResults();

        int pageSize = 3;
        List<List<Transaction>> pages = PaginationUtils.paginateResults(transactionList, pageSize);

        List<Transaction> currentPageResults = pages.get(currentPage - 1);
        model.addAttribute("transactions", currentPageResults);

        int totalPages = pages.size();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);

    //PaginationUtils.printPagination(currentPage, totalPages);

        return "results";

    }
}
