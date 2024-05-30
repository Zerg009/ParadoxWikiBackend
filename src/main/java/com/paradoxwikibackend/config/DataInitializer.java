package com.paradoxwikibackend.config;

import com.paradoxwikibackend.model.user.Role;
import com.paradoxwikibackend.model.user.User;
import com.paradoxwikibackend.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            // Check if an admin user already exists
            if (userRepository.findByEmail("admin@example.com").isEmpty()) {
                // Create the admin user
                User adminUser = User.builder()
                        .firstName("Admin")
                        .lastName("User")
                        .email("admin@example.com")
                        .password(passwordEncoder.encode("adminPassword"))
                        .role(Role.ADMIN)
                        .build();

                userRepository.save(adminUser);
            }
        };
    }
}
