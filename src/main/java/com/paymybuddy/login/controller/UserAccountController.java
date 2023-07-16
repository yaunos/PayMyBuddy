package com.paymybuddy.login.controller;

import com.paymybuddy.login.model.UserAccount;
import com.paymybuddy.login.repository.UserAccountRepository;
import com.paymybuddy.login.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Optional;


// @RestController
// ne fonctionne pas avec thymeleaf : on utilise l'annotation @Controller

@Controller
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @RequestMapping(value = {"/credit "}, method = RequestMethod.GET)
    public String home(Model model) {
        //return "home";

        return"redirect:/home";
    }

    /*
    @GetMapping("/home")user_account
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
    public String creditMyAccount(@RequestParam("transactionAmount") BigDecimal amount, HttpSession httpSession) {

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
        //double currentBalance = userAccount.getAccountBalance();
        //float currentBalance = userAccount.getAccountBalance();
        BigDecimal currentBalance = userAccount.getAccountBalance();
        //double newBalance = currentBalance + Long.parseLong(amount);
        BigDecimal newBalance = currentBalance.add(amount);
        //userAccount.setAccountBalance((long) newBalance);
        userAccount.setAccountBalance(newBalance);

        userAccountService.addUserAccount(userAccount);


        return"redirect:/home";
    }

    @PostMapping("/debit")
    public String debitMyAccount(@RequestParam("transactionAmount2") BigDecimal amount, HttpSession httpSession) {

        String email = (String) httpSession.getAttribute("email");
        System.out.println("Je veux verser de l'argent de payMyBuddy vers mon compte bancaire :");
        System.out.println(email);

        Optional<UserAccount> existingUserAccount = userAccountService.getUserAccountByEmail(email);
        UserAccount userAccount = existingUserAccount.get();
        System.out.println(userAccount.getEmail());
        //double currentBalance = userAccount.getAccountBalance();
        BigDecimal currentBalance = userAccount.getAccountBalance();
        //double newBalance = currentBalance - Long.parseLong(amount);
        BigDecimal newBalance = currentBalance.subtract(amount);
        //userAccount.setAccountBalance((long) newBalance);
        userAccount.setAccountBalance(newBalance);

        userAccountService.addUserAccount(userAccount);

        return"redirect:/home";
        //return null;
    }
}
