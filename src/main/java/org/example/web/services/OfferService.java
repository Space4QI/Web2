package org.example.web.services;

import org.example.web.DTO.OfferDTO;
import org.example.web.mappers.OfferMapper;
import org.example.web.models.Offer;
import org.example.web.repositories.ModelRepository;
import org.example.web.repositories.OfferRepository;
import org.example.web.repositories.UserEntityRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OfferService {

    private final OfferRepository offerRepository;

    private final OfferMapper offerMapper;

    private final UserEntityRepository userEntityRepository;

    private final ModelRepository modelRepository;


    public OfferService(OfferRepository offerRepository, OfferMapper offerMapper, UserEntityRepository userEntityRepository, ModelRepository modelRepository) {
        this.offerRepository = offerRepository;
        this.offerMapper = offerMapper;
        this.userEntityRepository = userEntityRepository;
        this.modelRepository = modelRepository;
    }

    public List<OfferDTO> getAllOffer() {
        return offerRepository.findAll()
                .stream()
                .map(offerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public OfferDTO getOfferById(UUID id) {
        Optional<Offer> offerOptional = offerRepository.findById(id);
        if (offerOptional.isPresent()) {
            return offerMapper.toDTO(offerOptional.get());
        } else {
            throw new NoSuchElementException("Offer with id " + id + " not found");
        }
    }

    public OfferDTO updateOffer(OfferDTO updatedOffer, UUID id) {
        Offer offer = offerRepository.findById(id).orElseThrow(NoSuchElementException::new);
        offer.setEngineType(Offer.EngineType.valueOf(updatedOffer.getEngineType()));
        offer.setTransmissionType(Offer.TransmissionType.valueOf(updatedOffer.getTransmissionType()));
        Offer updateOffer = offerRepository.save(offer);
        return offerMapper.toDTO(offer);
//        return offerRepository.findById(id)
//                .map(offer -> {
//                    if (updatedOffer.getDescription() != null) {
//                        offer.setDescription(updatedOffer.getDescription());
//                    }
//                    if (updatedOffer.getImageUrl() != null) {
//                        offer.setImageURL(updatedOffer.getImageUrl());
//                    }
//                    if (updatedOffer.getMileage() != null) {
//                        offer.setMileage(updatedOffer.getMileage());
//                    }
//                    if (updatedOffer.getPrice() != null) {
//                        offer.setPrice(updatedOffer.getPrice());
//                    }
//                    if (updatedOffer.getYear() != null) {
//                        offer.setYear(updatedOffer.getYear());
//                    }
//                    if (updatedOffer.getCreated() != null) {
//                        offer.setCreated(updatedOffer.getCreated());
//                    }
//                    if (updatedOffer.getModified() != null) {
//                        offer.setModified(updatedOffer.getModified());
//                    }
//                    if (updatedOffer.getEngine() != null) {
//                        offer.setEngineType(Offer.EngineType.valueOf(updatedOffer.getEngine()));
//                    }
//                    if (updatedOffer.getTransmission() != null) {
//                        offer.setTransmissionType(Offer.TransmissionType.valueOf(updatedOffer.getTransmission()));
//                    }
//                    return offerMapper.toDTO(offerRepository.save(offer));
//                })
//                .orElseThrow(() -> new EntityNotFoundException("Offer with id " + id + " not found"));
    }


    public OfferDTO saveOffer(OfferDTO offer) {
//        Offer temp = offerMapper.toEntity(offer);
//        UserEntity userEntity = userEntityRepository.findById(offer.getUserEntityId())
//                .orElseThrow(() -> new EntityNotFoundException("UserEntity with id " + offer.getUserEntityId() + " not found"));
//        Model model = modelRepository.findById(offer.getModelId())
//                .orElseThrow(() -> new EntityNotFoundException("Model with id " + offer.getModelId() + " not found"));
//        temp.setUserEntity(userEntity);
//        temp.setModel(model);
        Offer save = offerRepository.saveAndFlush(offerMapper.toEntity(offer));
        return offerMapper.toDTO(save);
    }

    public void deleteOffer(UUID id) {
        try {
            offerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Error: there is no element with " + id + " id");
        }
    }
}