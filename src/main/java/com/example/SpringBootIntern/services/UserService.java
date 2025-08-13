package com.example.SpringBootIntern.services;
import com.example.SpringBootIntern.ExceptionHandler.ValidationException;
import com.example.SpringBootIntern.Model.User;
import com.example.SpringBootIntern.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    public User createUser(User user) {

        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new ValidationException("Password is required");
        }
        if (user.getPassword().length() < 6 || user.getPassword().length() > 30) {
            throw new ValidationException("Password must be between 6 and 30 characters");
        }
        if(user.getRole().equals("ADMIN") == false && user.getRole().equals("USER") == false){
           throw new ValidationException("Role should be ADMIN OR USER"); 
         }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

}

