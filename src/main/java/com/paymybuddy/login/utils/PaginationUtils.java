package com.paymybuddy.login.utils;

import com.paymybuddy.login.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class PaginationUtils {

    public static List<List<Transaction>> paginateResults(List<Transaction> resultList, int pageSize) {
        List<List<Transaction>> pages = new ArrayList<>();
        for(int i = 0; i < resultList.size(); i+= pageSize) {
            List<Transaction> page = resultList.subList(i, Math.min(i + pageSize, resultList.size()));
            pages.add(page);
        }
        return pages;
    }

    public static void printPagination(int currentPage, int totalPages) {
        System.out.println("Précédent");
        for (int i = 1; i<= totalPages; i++) {
            if(i == currentPage) {
                System.out.print("[" + i + "]");
            } else {
                System.out.println(i + "");
            }
        }
        System.out.print("Suivant");
    }
}
