package com.paymybuddy.login.model;

import lombok.Data;

import javax.persistence.*;

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
