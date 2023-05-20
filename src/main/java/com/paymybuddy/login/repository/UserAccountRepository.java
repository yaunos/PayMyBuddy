package com.paymybuddy.login.repository;


import com.paymybuddy.login.model.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//public interface UserAccount extends JpaRepository

public interface UserAccountRepository extends CrudRepository<UserAccount, String> {

    Optional<UserAccount> findByEmail(String email);
}


