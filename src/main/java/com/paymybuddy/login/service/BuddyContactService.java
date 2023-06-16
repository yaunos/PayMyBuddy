package com.paymybuddy.login.service;


import com.paymybuddy.login.model.BuddyContact;
import com.paymybuddy.login.repository.BuddyContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuddyContactService {

    @Autowired
    private BuddyContactRepository buddyContactRepository;

    // Dependency injection
    @Autowired BuddyContactService(BuddyContactRepository buddyContactRepository) {
        this.buddyContactRepository = buddyContactRepository;
    }

    public Iterable<BuddyContact> getContacts() {
        return buddyContactRepository.findAll();
    }

    public Iterable<BuddyContact> getBuddyContacts() {
        return buddyContactRepository.findAll();
    }

    //public Optional<BuddyContactId> getBuddyContactsById(Integer id) {



    // Get contacts by user email
    //public Iterable<BuddyContact> findByUserName (String userName);



    // Add a new contact to your buddies


    public void addContactAsBuddy(BuddyContact buddyContact) {
        buddyContactRepository.save(buddyContact);

        }
}

