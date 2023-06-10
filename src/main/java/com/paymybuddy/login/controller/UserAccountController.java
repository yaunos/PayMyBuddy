package com.paymybuddy.login.controller;

import com.paymybuddy.login.model.UserAccount;
import com.paymybuddy.login.repository.UserAccountRepository;
import com.paymybuddy.login.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @RequestMapping(value = {"/credit "}, method = RequestMethod.GET)
    public String home(Model model) {
        return "home";
    }

    /*
    @GetMapping("/home")
    public String showLoginForm()
    {
        return "redirect:/home.html";
    }
    */



    @PostMapping("/home")
    public RedirectView home(@RequestParam String transactionAmount, HttpSession httpSession) {
        // on récupère l'email courant de la session
        String email = (String) httpSession.getAttribute("email");
        System.out.println(email);

        return null;
    }


    @PostMapping("/credit")
    public String creditMyAccount(@RequestParam("transactionAmount") String amount, HttpSession httpSession) {

        String email = (String) httpSession.getAttribute("email");
        System.out.println("Je veux ajouter de l'argent sur mon compte :");
        System.out.println(email);

        // On crédite (modifie) le userAccount courant
        // On crée ...
        //this.userAccountService = new UserAccountService();
        //this.userAccountRepository = new UserAccountRepository();

        Optional<UserAccount> existingUserAccount = userAccountService.getUserAccountByEmail(email);
        UserAccount userAccount = existingUserAccount.get();
        System.out.println(userAccount.getEmail());
        double currentBalance = userAccount.getAccountBalance();
        double newBalance = currentBalance + Long.parseLong(amount);
        userAccount.setAccountBalance((long) newBalance);

        userAccountService.addUserAccount(userAccount);

        return null;
    }


}
