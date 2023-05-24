package com.paymybuddy.login.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name= "user_account")
public class UserAccount {

    @Id
    @Column(name= "email" )
    private String email;

    @Column(name = "password" )
    private String password;

    @Column(name = "firstname" )
    private String firstname;

    @Column(name = "lastname" )
    private String lastname;

    @Column(name = "account_balance")
    private long accountBalance;

}
