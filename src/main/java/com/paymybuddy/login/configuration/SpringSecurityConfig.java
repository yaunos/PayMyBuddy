
package com.paymybuddy.login.configuration;


import com.paymybuddy.login.service.UserAccountDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;

// Indique à SpringBoot qu'il s'agit d'une classe de config
@Configuration

// Permet à SpringSecurity de savoir où se trouve la config web
@EnableWebSecurity

// Etendre cette classe avec WebSecurityConfigurer Adpater permet de gérer la chaîne de filtres
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserAccountDetailsServiceImpl userAccountDetailsService;

    @Autowired
    public SpringSecurityConfig(UserAccountDetailsServiceImpl userAccountDetailsService) {
        this.userAccountDetailsService = userAccountDetailsService;
    }

    // On définit Override pour que notre méthode prenne le pas sur celle de Spring
    @Override
    public void configure(HttpSecurity http) throws Exception {

        // Mise en place de la chaîne de filtres http

        http
            .csrf().disable()
            .authorizeRequests()
                //.antMatchers("/admin").hasRole("ADMIN")
                //.antMatchers("/user").hasRole("USER")
                .antMatchers("/login.html", "loginSuccess.html", "/loginFail.html").permitAll()

                //toutes les autres requêtes ... puis suivi de authenticated -> toutes les requêtes doivent être authentifiées par le login form
                .anyRequest().authenticated()

                /*
                .and()
                // formulaire OAuth2 par défaut ? Où est-il dans le projet ?
                .formLogin();
                */


                /*
            .and()
            .oauth2Login()
                .loginPage("/login.html")
                .defaultSuccessUrl("/loginSuccess.html", true)
                .failureUrl("/loginFail.html")
                .permitAll()

                 */

            .and()
            .logout()
            .permitAll();

        // Mise en place du formulaire de login terminée
    }

    // Mise en place de la méthode d'identification qui doit pouvoir prendre en compte un Active Directory, des identifiants de base de données
    // Utilisation de AuthentificationManagerBuilder
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {

        //auth.userDetailsService(userAccountDetailsService);

        auth.userDetailsService(username -> (UserDetails) userAccountDetailsService);

        /*

        auth.inMemoryAuthentication()
                .withUser("springuser").password(passwordEncoder().encode("spring123").roles("USER"))
                .and()
                .withUser("springadmin").password(passwordEncoder().encode("admin123").roles("ADMIN", "USER"));

         */
    }
    /*
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BcryptPasswordEncoder();
    }
    */

    }

