package com.paymybuddy.login.service;

import com.paymybuddy.login.model.UserAccount;
import com.paymybuddy.login.repository.UserAccountDetailsRepository;
import com.paymybuddy.login.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserAccountDetailsServiceImpl implements UserAccountDetailsRepository {
    private UserAccountRepository userAccountRepository;

    @Autowired
    public void UserDetailsServiceImpl(UserAccountRepository userAccountRepository)
    {
        this.userAccountRepository = userAccountRepository;
    }

    public UserAccountDetailsServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException
    {
        UserAccount userAccount = userAccountRepository.findById(email)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with email: " + email));

        return new org.springframework.security.core.userdetails.User(userAccount.getEmail(), userAccount.getPassword(), new ArrayList<>());
    }

}
