package com.paymybuddy.login.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data

@Entity
@Table(name= "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_number")
    private long transactionNumber;

    //@ManyToOne(cascade = CascadeType.MERGE)
    //@JoinColumn(name = "user_email")

    @Column(name = "from")
    private String userEmail;

    @Column(name = "to")
    private String buddyEmail;

    @Column(name = "date")
    private Date date;

    @Column(name = "transaction_amount")
    private long transactionAmount;

    @Column(name = "transaction_fee")
    private long transactionFee;

    @Column(name = "description")
    private String description;

}
