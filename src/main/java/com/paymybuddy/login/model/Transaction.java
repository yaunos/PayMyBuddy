package com.paymybuddy.login.model;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
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

    @Column(name = "from_email")
    private String userEmail;

    @Column(name = "to_email")
    private String buddyEmail;

    @Column(name = "date")
    private Date date;

    @Column(name = "transaction_amount")
    //private Long transactionAmount;
    private BigDecimal transactionAmount;

    @Column(name = "transaction_fee")
    //private long transactionFee;
    private BigDecimal transactionFee;

    @Column(name = "description")
    private String description;

}
