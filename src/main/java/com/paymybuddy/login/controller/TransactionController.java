package com.paymybuddy.login.controller;

import com.paymybuddy.login.model.Transaction;
import com.paymybuddy.login.model.UserAccount;
import com.paymybuddy.login.service.TransactionService;
import com.paymybuddy.login.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import static com.paymybuddy.login.constants.Constants.FeeRateForEachTransaction;

// @RestController
// ne fonctionne pas avec thymeleaf

@Controller
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
        String currentEmail = (String) httpSession.getAttribute("email");

        // On récupère les buddies de l'utilisateur connecté


        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //String currentUserAccountEmail = authentication.getName();
        //System.out.println(authentication.getName());

        // On liste les BuddyContact du UserAccount
        // System.out.println(httpSession.get)
        //List<BuddyContact> buddyContacts = buddyContactService.

        //On liste les transactions effectuées avec les BuddyContact

        System.out.println("*** Liste des transactions de l'utilisateur connecté ***");
        Iterable<Transaction> myTransactions = transactionService.getTransactionsOfConnectedUser(currentEmail);
        myTransactions.forEach(transaction -> System.out.println(transaction.getDescription()));
        myTransactions.forEach(transaction -> System.out.println(transaction.getBuddyEmail()));
        model.addAttribute("transactions", myTransactions);


        System.out.println("*** Liste des transactions de l'utilisateur courant ***");


        //return "home";
        return "transfer";
        //return "redirect:/transfer.html";

    }

    // On s'assure de la bonne gestion des transaction (commit et rollbacks)
    @Transactional
    @PostMapping(value = { "/transfer"})
    public String newTransaction(@RequestParam("email") String email, @RequestParam("transactionAmount" ) BigDecimal transactionAmount, @RequestParam("description") String description, HttpSession httpSession) {

        //
        //BigDecimal solde = new BigDecimal(@RequestParam("transactionAmount") BigDecimal transactionAmount));

        //On récupère les infos de la personne connectée

        // Par SecurityContextHolder
        // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // String currentUserAccountEmail = authentication.getName();

        // Par attribut de session
        String currentEmail = (String) httpSession.getAttribute("email");

        // Vérifier si le solde du compte courant est suffisant pour payer mon buddy
        if(transactionService.checkSuficientBalance(email, transactionAmount)) {

            // On récupère les informations du formulaire de paiement
            Transaction transaction = new Transaction();
            transaction.setUserEmail(currentEmail);
            transaction.setBuddyEmail(email);
            transaction.setDate(new Date());
            transaction.setDescription(description);
            //transaction.setTransactionAmount(transactionAmount);
            transaction.setTransactionAmount(transactionAmount);
            //transaction.setTransactionFee((Long) ((Long.valueOf(transactionAmount))*FeeRateForEachTransaction));
            transaction.setTransactionFee(transactionAmount.multiply(FeeRateForEachTransaction));

            // On enregistre la transaction
            transactionService.addTransaction(transaction);


            // On ajoute le montant de la transaction au compte de mon buddy
            Optional<UserAccount> existingUserAccount = userAccountService.getUserAccountByEmail(email);
            UserAccount userAccount = existingUserAccount.get();
            System.out.println(userAccount.getEmail());
            //double currentBalance2 = userAccount.getAccountBalance();
            BigDecimal currentBalance2 = userAccount.getAccountBalance();
            //double newBalance2 = currentBalance2 + Long.parseLong(transactionAmount);
            BigDecimal newBalance2 = currentBalance2.add(transactionAmount);
            //userAccount.setAccountBalance((long) newBalance2);
            userAccount.setAccountBalance(newBalance2);

            userAccountService.addUserAccount(userAccount);


            // On retranche le montant de la transaction et la commission au compte utilisateur courant
            Optional<UserAccount> existingBuddyAccount = userAccountService.getUserAccountByEmail(currentEmail);
            UserAccount buddyAccount = existingBuddyAccount.get();
            System.out.println(buddyAccount.getEmail());
            BigDecimal currentBalance = buddyAccount.getAccountBalance();
            //Long newBalance = currentBalance - Long.parseLong(transactionAmount) - transaction.getTransactionFee();
            BigDecimal newBalance = currentBalance.subtract(transactionAmount).subtract(transaction.getTransactionFee());
            buddyAccount.setAccountBalance(newBalance);

            userAccountService.addUserAccount(buddyAccount);

        } else {
            //
            //redirectAttributes.addFlashAttribute
        }

        return"redirect:/transfer";
        //return "home";
    }
}
