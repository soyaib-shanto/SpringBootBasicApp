package com.example.SpringBootIntern.Model;


import jakarta.validation.constraints.Pattern;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
     @Pattern(regexp = "^(ADMIN|USER)$", message = "Please insert USER or ADMIN")
    private String role;
}

