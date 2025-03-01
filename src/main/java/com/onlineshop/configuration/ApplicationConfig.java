package com.onlineshop.configuration;

import com.onlineshop.Role;
import com.onlineshop.entity.User;
import com.onlineshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository){
        return args -> {
            if (userRepository. findByUsername("admin").isEmpty()){
                Role role = Role.ADMIN;
                User user = User .builder ()
                        .username("admin")
                        .password (passwordEncoder. encode ("admin"))
                        .role(role)
                        .build();

                userRepository.save(user);
            };
        };
    };
}
