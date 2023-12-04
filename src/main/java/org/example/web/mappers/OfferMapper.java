package org.example.web.mappers;

import org.example.web.DTO.OfferDTO;
import org.example.web.models.Offer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OfferMapper {
    private final ModelMapper offerMap;

    @Autowired
    public OfferMapper(ModelMapper offerMap){
        this.offerMap = offerMap;
    }

    public OfferDTO toDTO (Offer input) {
        return offerMap.map(input, OfferDTO.class);
    }

    public Offer toEntity (OfferDTO input) {
        return offerMap.map(input, Offer.class);
    }
}