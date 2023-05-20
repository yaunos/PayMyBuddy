
package com.paymybuddy.login.configuration;


import com.paymybuddy.login.service.UserAccountDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserAccountDetailsServiceImpl userAccountDetailsService;

    @Autowired
    public SpringSecurityConfig(UserAccountDetailsServiceImpl userAccountDetailsService) {
        this.userAccountDetailsService = userAccountDetailsService;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                //.antMatchers("/admin").hasRole("ADMIN")
                //.antMatchers("/user").hasRole("USER")
                .anyRequest().authenticated()

                //.and()
                // formulaire OAuth2 par défaut ? Où est-il dans le projet ?
                //.formLogin();

            .and()
            .oauth2Login()
                .loginPage("/login.html")
                .defaultSuccessUrl("/loginSuccess.html", true)
                .failureUrl("/loginFailure.html")
                .permitAll()
            .and()
            .logout()
            .permitAll();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {

        //auth.userDetailsService(userAccountDetailsService);

        auth.userDetailsService(username -> (UserDetails) userAccountDetailsService);
    }
}

