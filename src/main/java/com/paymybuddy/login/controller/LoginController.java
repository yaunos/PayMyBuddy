package com.paymybuddy.login.controller;

import com.paymybuddy.login.model.UserAccount;
import com.paymybuddy.login.repository.UserAccountRepository;
import com.paymybuddy.login.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

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


    @GetMapping("/home")
    public String showLoginForm()
    {
        return "redirect:/login.html";
    }


    @PostMapping("/login")
    public RedirectView login(@RequestParam String email, @RequestParam String password, HttpSession httpSession) {
        // On vérifie les identifiants
        UserAccount foundUser = userAccountRepository.findById(String.valueOf(email)).orElse(null);

        // On s'assure que l'on récupère l'email de la personne connectée
        System.out.println(httpSession.getId());

        if (foundUser != null && PasswordUtils.comparePassword(password, foundUser.getPassword())) {

            // créer un objet en session ici
            httpSession.setAttribute("email", email);

            // créer ici l'objet
            //MyObject myObject = new MyObject();

            //return "L'objet a été stocké en session";







            //Renvoyer vers une page après succès d'authentification
            return new RedirectView("loginSuccess.html");
        }
        else
        {

            return new RedirectView("loginFail.html");
        }
    }

    /*
    @RequestMapping("/*")
    public String getGithub() {
        return "Welcome, Github User";
    }
    */


}

