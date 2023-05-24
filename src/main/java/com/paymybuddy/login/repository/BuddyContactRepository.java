package com.paymybuddy.login.repository;

import com.paymybuddy.login.model.BuddyContact;
import com.paymybuddy.login.model.BuddyContactId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

// public interface BuddyContactRepository extends CrudRepository <BuddyContactId, Integer>
public interface BuddyContactRepository extends CrudRepository<BuddyContact, BuddyContactId>
{


    Optional<BuddyContactId> findById(Integer id);
}
