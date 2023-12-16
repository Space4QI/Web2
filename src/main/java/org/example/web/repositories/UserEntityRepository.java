package org.example.web.repositories;

import org.example.web.models.Model;
import org.example.web.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findUserEntityByUsername(String username);

    Optional<UserEntity> findByEmail(String email);


    @Modifying
    @Transactional
    void deleteByUsername(String username);
}
