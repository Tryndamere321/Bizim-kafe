package az.sense.rasimkafesi.config;


import az.sense.rasimkafesi.models.Role;
import az.sense.rasimkafesi.models.UserEntity;
import az.sense.rasimkafesi.repositories.RoleRepository;
import az.sense.rasimkafesi.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Configuration
public class AdminAccountInitializer {

    @Bean
    CommandLineRunner initAdminAccount(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            Role adminRole = RoleRepository.findByName("ROLE_ADMIN");
            if (adminRole == null) {
                adminRole = new Role();
                adminRole.setName("ROLE_ADMIN");
                roleRepository.save(adminRole);
            }

            if (userRepository.findByEmail("info@zahid.com") == null) {
                UserEntity admin = new UserEntity();
                admin.setName("Zahid");
                admin.setSurname("Allahverenov");
                admin.setEmail("info@zahid.com");
                admin.setPassword(passwordEncoder.encode("Tahir<3"));
                admin.setRoles(Collections.singletonList(adminRole));
                userRepository.save(admin);
            }
        };
    }
}

