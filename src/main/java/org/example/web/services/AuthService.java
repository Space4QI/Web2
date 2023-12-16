package org.example.web.services;


import org.example.web.DTO.UserEntityDTO;
import org.example.web.DTO.UserRegistrationDTO;
import org.example.web.models.UserEntity;
import org.example.web.repositories.UserEntityRepository;
import org.example.web.repositories.UserRoleRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.example.web.models.UserRole;


import java.util.List;
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

    public void register(UserRegistrationDTO registrationDTO) {
        if (!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())) {
            throw new RuntimeException("passwords.match");
        }

        Optional<UserEntity> byEmail = this.userEntityRepository.findByEmail(registrationDTO.getEmail());

        if (byEmail.isPresent()) {
            throw new RuntimeException("email.used");
        }

        var userRole = userRoleRepository.findUserRoleByName(UserRole.RoleType.USER.name()).orElseThrow();

        UserEntity userEntity = new UserEntity();

        userEntity.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        userEntity.setFirstName(registrationDTO.getFirstName());
        userEntity.setLastName(registrationDTO.getLastName());
        userEntity.setUserRole(userRole);
        userEntity.getEmail();

        this.userEntityRepository.save(userEntity);
    }
//                registrationDTO.getUsername(),
//                registrationDTO.getEmail(),
//                registrationDTO.getFirstName(),
//                registrationDTO.getLastName(),
//                registrationDTO.getPassword(),
//                registrationDTO.getConfirmPassword()

    public UserEntity getUser(String username) {
        return userEntityRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));
    }
}



