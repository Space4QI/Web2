package org.example.web;

import org.example.web.models.UserEntity;
import org.example.web.models.UserRole;
import org.example.web.repositories.UserEntityRepository;
import org.example.web.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Init implements CommandLineRunner {
    private final UserEntityRepository userEntityRepository;

    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final String defaultPassword;

    public Init(UserEntityRepository userEntityRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, @Value("${app.default.password}") String defaultPassword) {
        this.userEntityRepository = userEntityRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.defaultPassword = defaultPassword;
    }

    @Override
    public void run(String... args) throws Exception {
        //initRoles();
        //initUsers();
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            UserRole adminRole = new UserRole();
            adminRole.setRoleType(UserRole.RoleType.ADMIN);
            userRoleRepository.save(adminRole);

            UserRole normalUserRole = new UserRole();
            normalUserRole.setRoleType(UserRole.RoleType.USER);
            userRoleRepository.save(normalUserRole);
        }
    }


    private void initUsers() {
        if (userEntityRepository.count() == 0) {
            initAdmin();
            initNormalUser();
        }
    }

    private void initAdmin() {
        var adminRole = userRoleRepository
                .findUserRoleByName(UserRole.RoleType.ADMIN.name())
                .orElseThrow(() -> new RuntimeException("Admin role not found"));

        var adminUser = new UserEntity();
        adminUser.setUsername("admin");
        adminUser.setPassword(passwordEncoder.encode(defaultPassword));
        adminUser.setEmail("admin@example.com");
        adminUser.setFirstName("Admin");
        adminUser.setLastName("Adminovich");
        adminUser.setAge(30);
        adminUser.setUserRole(adminRole);

        userEntityRepository.save(adminUser);
    }

    private void initNormalUser() {
        var userRole = userRoleRepository.findUserRoleByName(UserRole.RoleType.USER.name()).orElseThrow();

        var normalUser = new UserEntity();
        normalUser.setUsername("user");
        normalUser.setPassword(passwordEncoder.encode(defaultPassword));
        normalUser.setEmail("user@example.com");
        normalUser.setFirstName("User");
        normalUser.setLastName("Userovich");
        normalUser.setAge(22);
        normalUser.setUserRole(userRole);

        userEntityRepository.save(normalUser);
    }

}