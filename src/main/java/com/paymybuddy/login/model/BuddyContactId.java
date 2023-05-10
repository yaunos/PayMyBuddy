package com.paymybuddy.login.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Embeddable // Dans la classe de représentation
public class BuddyContactId implements Serializable {

    //@Id
    @Column(name = "buddy_email")
    private String buddy_email;

    //@Id
    @Column(name = "user_email")
    private String user_email;

    public String getBuddy_email() {
        return buddy_email;
    }

    public void setBuddy_email(String buddy_email) {
        this.buddy_email = buddy_email;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuddyContactId that = (BuddyContactId) o;
        return Objects.equals(buddy_email, that.buddy_email) && Objects.equals(user_email, that.user_email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buddy_email, user_email);
    }
}
