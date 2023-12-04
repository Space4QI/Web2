package org.example.web.services;

import org.example.web.DTO.UserEntityDTO;
import org.example.web.mappers.UserEntityMapper;
import org.example.web.models.UserEntity;
import org.example.web.repositories.UserEntityRepository;
import org.example.web.repositories.UserRoleRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserEntityService {

    private final UserEntityRepository userEntityRepository;

    private final UserEntityMapper userEntityMapper;

    private final UserRoleRepository userRoleRepository;

    private final UserRoleService userRoleService;

    public UserEntityService(UserEntityRepository userEntityRepository, UserEntityMapper userEntityMapper, UserRoleRepository userRoleRepository, UserRoleService userRoleService) {
        this.userEntityRepository = userEntityRepository;
        this.userEntityMapper = userEntityMapper;
        this.userRoleRepository = userRoleRepository;
        this.userRoleService = userRoleService;
    }

    public List<UserEntityDTO> getAllUserEntity() {
        return userEntityRepository.findAll()
                .stream()
                .map(userEntityMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserEntityDTO getUserEntityById(UUID id) {
        Optional<UserEntity> userEntityOptional = userEntityRepository.findById(id);
        if (userEntityOptional.isPresent()) {
            return userEntityMapper.toDTO(userEntityOptional.get());
        } else {
            throw new NoSuchElementException("UserEntity with id " + id + " not found");
        }
    }

    public UserEntityDTO updateUserEntity(UserEntityDTO updatedUserEntity, UUID id) {
        UserEntity userEntity = userEntityRepository.findById(id).orElseThrow(NoSuchElementException::new);
        UserEntity updateUserEntity = userEntityRepository.save(userEntity);
        return userEntityMapper.toDTO(updateUserEntity);
    }
//        return userEntityRepository.findById(id)
//                .map(userEntity -> {
//                    if (updatedUserEntity.getUsername() != null) {
//                        userEntity.setUsername(updatedUserEntity.getUsername());
//                    }
//                    if (updatedUserEntity.getPassword() != null) {
//                        userEntity.setPassword(updatedUserEntity.getPassword());
//                    }
//                    if (updatedUserEntity.getFirstName() != null) {
//                        userEntity.setFirstName(updatedUserEntity.getFirstName());
//                    }
//                    if (updatedUserEntity.getLastName() != null) {
//                        userEntity.setLastName(updatedUserEntity.getLastName());
//                    }
//                    if (updatedUserEntity.getImageUrl() != null) {
//                        userEntity.setImageURL(updatedUserEntity.getImageUrl());
//                    }
//                    if (updatedUserEntity.getCreated() != null) {
//                        userEntity.setCreated(updatedUserEntity.getCreated());
//                    }
//                    if (updatedUserEntity.getModified() != null) {
//                        userEntity.setModified(updatedUserEntity.getModified());
//                    }
//                    if (updatedUserEntity.getUserRole() != null) {
//                        userEntity.setUserRole(updatedUserEntity.getUserRole());
//                    }
//                    return userEntityMapper.toDTO(userEntityRepository.save(userEntity));
//                })
//                .orElseThrow(() -> new EntityNotFoundException("UserEntity with id " + id + " not found"));
//    }

    public UserEntityDTO saveUserEntity(UserEntityDTO userEntity) {
//        UserEntity temp = userEntityMapper.toEntity(userEntity);
//        UserRole userRole = userRoleRepository.findById(userEntity.getUserRoleId())
//                .orElseThrow(() -> new EntityNotFoundException("UserRole with id " + userEntity.getUserRoleId() + " not found"));
//        temp.setUserRole(userRole);
//            System.out.println("Received UserEntityDTO: " + userEntity.toString());
//
//            UserEntity entityToSave = userEntityMapper.toEntity(userEntity);
//            System.out.println("Entity to save: " + entityToSave.toString());
//
//            UserEntity savedEntity = userEntityRepository.save(entityToSave);
//            System.out.println("Saved UserEntity: " + savedEntity.toString());
        UserEntity save = userEntityRepository.save(userEntityMapper.toEntity(userEntity));
        return userEntityMapper.toDTO(save);

    }

    public void deleteUserEntity(UUID id) {
        try {
            userEntityRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Error: there is no element with " + id + " id");
        }
    }
}