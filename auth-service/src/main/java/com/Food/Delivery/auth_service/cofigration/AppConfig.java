package com.Food.Delivery.auth_service.cofigration;

import com.Food.Delivery.auth_service.entity.Users;
import com.Food.Delivery.auth_service.enums.UserRole;
import com.Food.Delivery.auth_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AppConfig implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository ;
    @Autowired
    private PasswordEncoder passwordEncoder ;
    @Override
    public void run(String... args) throws Exception {
        if(!userRepository.existsByEmail("admin@food.com"))
        {
            Users users = Users.builder()
                    .name("ADMIN")
                    .email("admin@food.com")
                    .password(passwordEncoder.encode("123456"))
                    .userRole(UserRole.ADMIN)
                    .phone("01123264192")
                    .build() ;
            userRepository.save(users) ;
            System.out.println("Admin Success Login");
        }
    }
}
