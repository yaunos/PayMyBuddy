package com.paymybuddy.login.service;

import com.paymybuddy.login.model.UserAccount;
import com.paymybuddy.login.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    public Iterable<UserAccount> getUserAccounts() {
        return userAccountRepository.findAll();

    }

    public Optional<UserAccount> getUserAccountByEmail(String email) {
        return userAccountRepository.findByEmail(email);

    }

    /**
     *  Add, modify (delete ?) User Accounts
     */
    public UserAccount addUserAccount(UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }


}
