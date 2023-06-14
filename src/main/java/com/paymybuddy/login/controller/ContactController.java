package com.paymybuddy.login.controller;

import com.paymybuddy.login.model.BuddyContact;
import com.paymybuddy.login.model.UserAccount;
import com.paymybuddy.login.service.BuddyContactService;
import com.paymybuddy.login.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


// @RestController
// L'annotation ci-dessus ne fonctionne pas avec Thymeleaf

@Controller
public class ContactController {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private BuddyContactService buddyContactService;

/* A effacer ou remplacer par example controller
    @GetMapping("/example")
    public String home(Model model) {
        model.addAttribute("title", "Maison");
        return "example";
    }

 */



    @GetMapping(value = {"/contact"})

    public List<String> getUsersEmailsExceptedMe(Model model, HttpSession httpSession) {
        List<String> usersEmailsExceptedMe = new ArrayList<>();

        // On récupère l'email de l'utilisateur connecté
        String currentEmail = (String) httpSession.getAttribute("email");

        Iterable<UserAccount> allUserAccounts = userAccountService.getUserAccounts();
        for (UserAccount userAccount : allUserAccounts)
            if (!userAccount.getEmail().equalsIgnoreCase(currentEmail)) {
                usersEmailsExceptedMe.add(userAccount.getEmail());
            }

        model.addAttribute("usersEmailsExceptedMe", usersEmailsExceptedMe);


        //return new RedirectView("contact.html");
        //return Collections.singletonList("example");
        return usersEmailsExceptedMe;
        //return (List<String>) new RedirectView("/contact.html");
        //return Collections.singletonList("redirect:/contact.html");
        }

    @PostMapping(value = {"/contact"})
    public String addBuddy(@RequestParam String email, Model model, HttpSession httpsession){
        // on récupère l'email de l'utilisateur connecté
        String currentEmail = (String) httpsession.getAttribute("email");

        // On récupère les buddies de l'utilisateur connecté
        Iterable<BuddyContact> buddyContacts = buddyContactService.getBuddyContacts();
        BuddyContact buddyContact = new BuddyContact();

        // on crée un nouveau couple userEmail, buddyEmail
        //buddyContact.setBuddyContactId(currentEmail, );

        // On enregistre le nouvel ami (nouveau buddy parmi les utilisateurs de payMyBuddy)
        //buddyContactService.

        return "redirect:/contact.html";
    }


}
