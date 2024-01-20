package org.example.web.repositories;

import org.example.web.models.Brand;
import org.example.web.models.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ModelRepository extends JpaRepository<Model, String> {
    Optional<Model> findModelByName(String name);

    @Modifying
    @Transactional
    void deleteByName(String name);
}
