package com.paymybuddy.login.repository;

import com.paymybuddy.login.model.BuddyContact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
//public interface BuddyContactRepository extends CrudRepository <BuddyContactId, Integer>
public interface BuddyContactRepository extends CrudRepository <BuddyContact, String>
{
    Iterable<BuddyContact> findAll();
    //static Optional<BuddyContact> save() {return null;}

    //public Iterable<BuddyContact> findAllBy

    // Erreur
    //public Iterable<BuddyContact> findBuddyContactByUserEmail(String userEmail);

}
