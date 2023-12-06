package org.example.web.repositories;

import org.example.web.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BrandRepository extends JpaRepository<Brand, UUID>{
    Optional<Brand> findBrandByName(String name);

    @Modifying
    @Transactional
    void deleteByName(String name);
}
