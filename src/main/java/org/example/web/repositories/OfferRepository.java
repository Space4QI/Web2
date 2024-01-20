package org.example.web.repositories;

import org.example.web.models.Model;
import org.example.web.models.Offer;
import org.example.web.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OfferRepository extends JpaRepository<Offer, String> {

    Optional<Offer> findOfferByUuid(String uuid);

    List<Offer> findByPriceBetween(double minPrice, double maxPrice);

    List<Offer> findAllBySeller(UserEntity seller);

    @Modifying
    @Transactional
    void deleteById(String uuid);
}
