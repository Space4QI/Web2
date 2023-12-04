package org.example.web.mappers;

import org.example.web.DTO.BrandDTO;
import org.example.web.models.Brand;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BrandMapper {
    private final ModelMapper brandMap;


    @Autowired
    public BrandMapper(ModelMapper brandMap){
        this.brandMap = brandMap;
    }

    public BrandDTO toDTO (Brand input) {
        return brandMap.map(input, BrandDTO.class);
    }

    public Brand toEntity (BrandDTO input) {
        return brandMap.map(input, Brand.class);
    }
}