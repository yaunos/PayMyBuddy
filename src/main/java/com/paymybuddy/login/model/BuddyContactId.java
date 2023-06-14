package com.paymybuddy.login.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
// Dans la classe de représentation
public class BuddyContactId implements Serializable {

    //@Id
    //@Column(name = "buddy_email")


    //private String buddy_email;
    private String buddyEmail;

    //@Id
    //@Column(name = "user_email")
    private String userEmail;

    // default constructor
    public
    BuddyContactId(String buddyEmail, String userEmail) {
        this.buddyEmail = buddyEmail;
        this.userEmail = userEmail;
    }

    public BuddyContactId() {

    }

    // getters, equals() and hashcode() methods
    public String getBuddyEmail() {
        return buddyEmail;
    }

    public void setBuddyEmail(String buddy_email) {
        this.buddyEmail = buddyEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String user_email) {
        this.userEmail = userEmail;
    }

    // equals() method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuddyContactId that = (BuddyContactId) o;
        return Objects.equals(buddyEmail, that.buddyEmail) && Objects.equals(userEmail, that.userEmail);
    }


    // hashCode() method
    @Override
    public int hashCode() {
        return Objects.hash(buddyEmail, userEmail);
    }
}
