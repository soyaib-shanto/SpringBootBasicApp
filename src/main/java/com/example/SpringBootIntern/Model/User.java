package com.example.SpringBootIntern.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class User {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Username must not be empty")
     private String username;

    @Column(nullable = false)
    @NotBlank(message = "Password must not be empty")
   // @Size(min = 6, max = 30, message = "Password must be between 6 and 30 characters")
     private String password;

     @Column(nullable = false)
     @Pattern(regexp = "^(ADMIN|USER)$", message = "Please insert USER or ADMIN")
      private String role;
}

