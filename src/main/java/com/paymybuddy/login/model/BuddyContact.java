package com.paymybuddy.login.model;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data

@Entity
@Table(name = "buddy_contact")
public class BuddyContact implements Serializable {

    @EmbeddedId
    private BuddyContactId buddyContactId;

    // constructors, getters and setters

    // constructor
    public BuddyContact(BuddyContactId buddyContactId) {
       this.buddyContactId = buddyContactId;
   }

    public BuddyContact() {

    }

    // getters and setters
    public BuddyContactId getBuddyContactId() {
        return buddyContactId;
    }

    public void setBuddyContactId(BuddyContactId buddyContactId) {
        this.buddyContactId = buddyContactId;
    }
}


