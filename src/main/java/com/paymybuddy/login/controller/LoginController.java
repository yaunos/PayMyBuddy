package com.paymybuddy.login.controller;

import com.paymybuddy.login.model.Transaction;
import com.paymybuddy.login.model.UserAccount;
import com.paymybuddy.login.repository.UserAccountRepository;
import com.paymybuddy.login.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
public class LoginController {
    /*
    @Autowired
    private BuddyContactService buddyContactService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserAccountService userAccountService;
    */


    @Autowired
    private UserAccountRepository userAccountRepository;

    //@Autowired
    //private BuddyContactRepository buddyContactRepository;

    @RequestMapping(value = {"/", "/home "}, method = RequestMethod.GET)
    public String home(Model model) {
        return "home";
    }


    @GetMapping("/login")
    public String showLoginForm()
    {
        return "redirect:/login.html";
    }


    @PostMapping("/login")
    public RedirectView login(@RequestParam String email, @RequestParam String password)
    {
        UserAccount foundUser = userAccountRepository.findById(String.valueOf(email)).orElse(null);
        if (foundUser != null && PasswordUtils.comparePassword(password,foundUser.getPassword()))
        {
            return new RedirectView("loginSuccess.html");
        }
        else
        {

            return new RedirectView("loginFail.html");
        }
    }

    @GetMapping(value = {"/transfer"})
    public String transfer(Model model) {

        ///On récupère les infos de la personne connectée

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserAccountEmail = authentication.getName();

        // On liste les BuddyContact du UserAccount

        //On affiche les transactions effectuées avec les BuddyContact

        List<Transaction> transactions;

        return "home";

    }

    @PostMapping(value = { "/transfer"})
    public String newTransaction(Model model) {

        //On récupère les infos de la personne connectée

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserAccountEmail = authentication.getName();

        // On récupère les informations du formulaire de paiement

        //On fait une transaction entre les 2 comptes

        return "home";
    }

    /*
    @RequestMapping("/*")
    public String getGithub() {
        return "Welcome, Github User";
    }
    */



}
