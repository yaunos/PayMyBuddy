package com.paymybuddy.login.repository;

import com.paymybuddy.login.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaginationRepository extends JpaRepository <Transaction, Long> {

}
