package com.paymybuddy.login.service;


import com.paymybuddy.login.model.BuddyContact;
import com.paymybuddy.login.model.BuddyContactId;
import com.paymybuddy.login.repository.BuddyContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service public class BuddyContactService {

    @Autowired
    private BuddyContactRepository buddyContactRepository;

    public Iterable<BuddyContactId> getBuddyContacts() {
        return buddyContactRepository.findAll();
    }

    public Optional<BuddyContactId> getBuddyContactsById(Integer id) {
        return buddyContactRepository.findById(id);
    }
}
