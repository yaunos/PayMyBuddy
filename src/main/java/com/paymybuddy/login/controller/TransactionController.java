package com.paymybuddy.login.controller;

import com.paymybuddy.login.model.Transaction;
import com.paymybuddy.login.model.UserAccount;
import com.paymybuddy.login.service.TransactionService;
import com.paymybuddy.login.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

import static com.paymybuddy.login.constants.Constants.FeeRateForEachTransaction;

@RestController
public class TransactionController {
    private final TransactionService transactionService;


    //private final UserAccountService userAccountService;
    @Autowired
    private UserAccountService userAccountService;

    /*
    @Autowired
    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }
    */


    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping(value = {"/transfer"})
    public String transfer(Model model, HttpSession httpSession) {

        ///On récupère les infos de la personne connectée
        System.out.println(httpSession.getAttribute("email"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //String currentUserAccountEmail = authentication.getName();
        System.out.println(authentication.getName());

        // On liste les BuddyContact du UserAccount
        // System.out.println(httpSession.get)
        //List<BuddyContact> buddyContacts = BuddyContactService //

        //On affiche les transactions effectuées avec les BuddyContact

        // // List<Transaction> transactions = TransactionService.;

        return "home";

    }

    // On s'assure de la bonne gestion des transaction (commit et rollbacks)
    @Transactional
    @PostMapping(value = { "/transfer"})
    public String newTransaction(@RequestParam("email") String email, @RequestParam("transactionAmount" ) String transactionAmount, @RequestParam("description") String description, HttpSession httpSession) {

        //On récupère les infos de la personne connectée

        // Par SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserAccountEmail = authentication.getName();

        // Par attribut de session
        String currentEmail = (String) httpSession.getAttribute("email");

        // On récupère les informations du formulaire de paiement
        Transaction transaction = new Transaction();
        transaction.setUserEmail(currentEmail);
        transaction.setBuddyEmail(email);
        transaction.setDate(new Date());
        transaction.setDescription(description);
        transaction.setTransactionAmount(Long.parseLong(transactionAmount));
        transaction.setTransactionFee((long) ((Long.valueOf(transactionAmount))*FeeRateForEachTransaction));

        // On enregistre la transaction
        transactionService.addTransaction(transaction);


        // On ajoute le montant de la transaction au compte de mon buddy
        Optional<UserAccount> existingUserAccount = userAccountService.getUserAccountByEmail(email);
        UserAccount userAccount = existingUserAccount.get();
        System.out.println(userAccount.getEmail());
        double currentBalance2 = userAccount.getAccountBalance();
        double newBalance2 = currentBalance2 - Long.parseLong(transactionAmount);
        userAccount.setAccountBalance((long) newBalance2);

        userAccountService.addUserAccount(userAccount);


        // On retranche le montant de la transaction et la commission au compte utilisateur courant
        Optional<UserAccount> existingBuddyAccount = userAccountService.getUserAccountByEmail(currentEmail);
        UserAccount buddyAccount = existingBuddyAccount.get();
        System.out.println(buddyAccount.getEmail());
        double currentBalance = buddyAccount.getAccountBalance();
        double newBalance = currentBalance - Long.parseLong(transactionAmount) - transaction.getTransactionFee();
        buddyAccount.setAccountBalance((long) newBalance);

        userAccountService.addUserAccount(buddyAccount);






        //
        return "home";
    }
}
