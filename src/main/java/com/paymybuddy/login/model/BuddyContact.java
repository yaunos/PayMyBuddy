package com.paymybuddy.login.model;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data

@Entity
@Table(name = "buddy_contact")
public class BuddyContact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buddy_link_number")
    private long transactionNumber;

    //@Id
    @Column(name = "user_email")
    private String userEmail;


    @Column(name = "buddy_email")
    private String buddyEmail;


    // default constructor
    public
    BuddyContact(String buddyEmail, String userEmail) {
        this.buddyEmail = buddyEmail;
        this.userEmail = userEmail;
    }
    // constructors, getters and setters


    public BuddyContact() {

    }



    // added
    public String getBuddyEmail() {
        return userEmail;
    }

    public void setBuddyEmail(String buddyEmail) {
        this.buddyEmail = buddyEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

}


