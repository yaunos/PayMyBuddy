package com.paymybuddy.login.repository;

import com.paymybuddy.login.model.BuddyContactId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuddyContactRepository extends CrudRepository <BuddyContactId, Integer> {


}
