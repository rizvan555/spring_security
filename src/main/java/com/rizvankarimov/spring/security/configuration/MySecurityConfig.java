package com.rizvankarimov.spring.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.withDefaultPasswordEncoder()
                .username("rizvan")
                .password("test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails user2 = User.withDefaultPasswordEncoder()
                .username("mary")
                .password("test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails user3 = User.withDefaultPasswordEncoder()
                .username("susan")
                .password("test123")
                .roles("EMPLOYEE", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1, user2, user3);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

     return http
            .authorizeHttpRequests(auth -> {
                auth.requestMatchers("/", "/login").permitAll();
                auth.requestMatchers("/hr_info/**").hasRole("EMPLOYEE");
                auth.requestMatchers("/manager_info/**").hasRole("MANAGER");
                auth.anyRequest().authenticated();
                    })
            .formLogin(Customizer.withDefaults())
            .build();
    }
}
