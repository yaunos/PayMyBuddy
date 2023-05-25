package com.paymybuddy.login.controller;

import com.paymybuddy.login.model.UserAccount;
import com.paymybuddy.login.repository.UserAccountRepository;
import com.paymybuddy.login.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
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

}
