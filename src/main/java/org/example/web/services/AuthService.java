package org.example.web.services;


import org.example.web.DTO.UserRegistrationDTO;
import org.example.web.models.UserEntity;
import org.example.web.models.UserRole;
import org.example.web.repositories.UserEntityRepository;
import org.example.web.repositories.UserRoleRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private UserEntityRepository userEntityRepository;
    private UserRoleRepository userRoleRepository;
    private PasswordEncoder passwordEncoder;

    public AuthService(UserEntityRepository userEntityRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userEntityRepository = userEntityRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
    }

    public void register(UserRegistrationDTO userRegistrationDTO) {
        if (!userRegistrationDTO.getPassword().equals(userRegistrationDTO.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        this.userEntityRepository.findByEmail(userRegistrationDTO.getEmail())
                .ifPresent(existingUser -> {
                    throw new DuplicateKeyException("Email is already in use");
                });

        UserRole userRole = userRoleRepository.findUserRoleByName(UserRole.RoleType.USER.name())
                .orElseThrow(() -> new IllegalArgumentException("User role not found"));

        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        userEntity.setUsername(userRegistrationDTO.getUsername());
        userEntity.setFirstName(userRegistrationDTO.getFirstName());
        userEntity.setLastName(userRegistrationDTO.getLastName());
        userEntity.setUserRole(userRole);
        userEntity.setEmail(userRegistrationDTO.getEmail());

        this.userEntityRepository.save(userEntity);
    }
//                registrationDTO.getUsername(),
//                registrationDTO.getEmail(),
//                registrationDTO.getFirstName(),
//                registrationDTO.getLastName(),
//                registrationDTO.getPassword(),
//                registrationDTO.getConfirmPassword()

    public boolean isUsernameUnique(String username) {
        return userEntityRepository.findUserEntityByUsername(username).isEmpty();
    }

    public boolean isEmailUnique(String email) {
        return userEntityRepository.findByEmail(email).isEmpty();
    }

    public UserEntity getUser(String username) {
        return userEntityRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));
    }
}



