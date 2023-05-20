package com.paymybuddy.login.repository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserAccountDetailsRepository {
    UserDetails loadUserByEmail(String email) throws UsernameNotFoundException;
}
