package com.example.SpringBootIntern.Config;

import com.example.SpringBootIntern.Model.User;
import com.example.SpringBootIntern.Repository.UserRepository;
import com.example.SpringBootIntern.services.CustomUserDetailsService;
import com.example.SpringBootIntern.services.UserService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .userDetailsService(userDetailsService)
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/public").permitAll()
                    .requestMatchers("/user").hasAnyRole("USER", "ADMIN")
                    .requestMatchers("/admin", "/users").hasRole("ADMIN")
                    .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public CommandLineRunner init(UserRepository repo, UserService service) {
        return args -> {


            if (repo.findByUsername("intern").isEmpty()) {
                User user = new User("intern", "password123", "USER");
                service.createUser(user);
            }
            if (repo.findByUsername("admin").isEmpty()) {
                User user = new User("admin", "admin123", "ADMIN");
                  service.createUser(user);
            }
        };
    }
      

}

