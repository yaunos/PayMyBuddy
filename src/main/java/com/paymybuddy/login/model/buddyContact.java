package com.paymybuddy.login.model;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data

@Entity
@Table(name = "buddy_contact")


public class buddyContact implements Serializable {

    @EmbeddedId
    private BuddyContactId buddyContactId;

    // constructors, getters and setters

}


