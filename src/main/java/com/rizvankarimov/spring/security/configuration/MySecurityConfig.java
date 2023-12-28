package com.rizvankarimov.spring.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {

    @Autowired
    DataSource dataSource;

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user1 = User.withDefaultPasswordEncoder()
//                .username("rizvan")
//                .password("test123")
//                .roles("EMPLOYEE")
//                .build();
//
//        UserDetails user2 = User.withDefaultPasswordEncoder()
//                .username("mary")
//                .password("test123")
//                .roles("HR")
//                .build();
//
//        UserDetails user3 = User.withDefaultPasswordEncoder()
//                .username("susan")
//                .password("test123")
//                .roles("MANAGER","HR")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1, user2, user3);
//    }



    //Burada deyirik ki, userlerin datalarini database-den gotur
    @Bean
    public UserDetailsService userDetailsService() {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username=?");
        userDetailsManager.setAuthoritiesByUsernameQuery("SELECT username, authority FROM authorities WHERE username=?");
        return userDetailsManager;
    }




    //Burada deyirik ki, bu filteri istifade et ve buna uygun authorizationlari ver
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

     return http
            .authorizeHttpRequests(auth -> {
                auth.requestMatchers("/", "/login").permitAll();
                auth.requestMatchers("/hr_info/**").hasRole("HR");
                auth.requestMatchers("/manager_info/**").hasRole("MANAGER");
                auth.anyRequest().authenticated();
                    })
            .formLogin(Customizer.withDefaults())
            .build();
    }
}
