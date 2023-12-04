package org.example.web.services;

import org.example.web.DTO.UserRoleDTO;
import org.example.web.mappers.UserRoleMapper;
import org.example.web.models.UserRole;
import org.example.web.repositories.UserRoleRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    private final UserRoleMapper userRoleMapper;

    public UserRoleService(UserRoleRepository userRoleRepository, UserRoleMapper userRoleMapper) {
        this.userRoleRepository = userRoleRepository;
        this.userRoleMapper = userRoleMapper;
    }

    public List<UserRoleDTO> getAllUserRoles() {
        return userRoleRepository.findAll()
                .stream()
                .map(userRoleMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserRoleDTO getUserRoleById(UUID id) {
        UserRole userRole = userRoleRepository.findById(id).orElseThrow(RuntimeException::new);
        return userRoleMapper.toDTO(userRole);
    }

    public UserRoleDTO updateUserRole(UserRoleDTO updatedUserRole, UUID id) {
        UserRole userRole = userRoleRepository.findById(id).orElseThrow(NoSuchElementException::new);
        userRole.setRoleType(UserRole.RoleType.valueOf(updatedUserRole.getRoleType()));
        UserRole updateUserRole = userRoleRepository.saveAndFlush(userRole);
        return userRoleMapper.toDTO(updateUserRole);
    }

    public UserRoleDTO saveUserRole(UserRoleDTO userRole) {
        return userRoleMapper.toDTO(userRoleRepository.saveAndFlush(userRoleMapper.toEntity(userRole)));
    }

    public void deleteUserRole(UUID id) {
        try {
            userRoleRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Error: there is no element with " + id + " id");
        }
    }
}
