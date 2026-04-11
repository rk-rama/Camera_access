package com.Juan.Aliya.config;

import com.Juan.Aliya.model.User;
import com.Juan.Aliya.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Check karein ki "admin" pehle se hai ya nahi
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setFullName("System Admin");
                admin.setUsername("admin"); // Aapka Fix ID

                // Password ko encrypt karke save karna zaroori hai
                admin.setPassword(passwordEncoder.encode("admin123")); // Aapka Fix Password

                admin.setRole("ROLE_ADMIN");

                userRepository.save(admin);
                System.out.println("✅ Fixed Admin Account Created: admin / admin123");
            } else {
                System.out.println("ℹ️ Admin account already exists.");
            }
        };
    }
}