package org.example.web.services;

import org.example.web.DTO.BrandDTO;
import org.example.web.mappers.BrandMapper;
import org.example.web.models.Brand;
import org.example.web.repositories.BrandRepository;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BrandService {

    private final BrandRepository brandRepository;

    private final BrandMapper brandMapper;

    private final ModelMapper modelMapper;

    public BrandService(BrandRepository brandRepository, BrandMapper brandMapper, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.brandMapper = brandMapper;
        this.modelMapper = modelMapper;
    }

    public List<BrandDTO> getAllBrands() {
        return brandRepository.findAll()
                .stream()
                .map(brandMapper::toDTO)
                .collect(Collectors.toList());
    }

    public BrandDTO getBrandById(UUID id) {
        Brand brand = brandRepository.findById(id).orElseThrow(RuntimeException::new);
        return brandMapper.toDTO(brand);
    }

    public BrandDTO addBrand(BrandDTO brandDto) {
        Brand brand = modelMapper.map(brandDto, Brand.class);
        Brand addBrand = brandRepository.saveAndFlush(brand);
        return modelMapper.map(addBrand, BrandDTO.class);
    }

    public BrandDTO updateBrand(UUID id, BrandDTO updatedBrand) {
        return brandRepository.findById(id)
                .map(brand -> {
                    brand.setName(updatedBrand.getName());
                    return brandMapper.toDTO(brandRepository.saveAndFlush(brand));
                })
                .orElseThrow(() -> new IllegalArgumentException("dsd"));
    }

    public BrandDTO saveBrand(BrandDTO brand) {
        return brandMapper.toDTO(brandRepository.saveAndFlush(brandMapper.toEntity(brand)));
    }

    public void deleteBrand(UUID id) {
        try {
            brandRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Error: there is no element with " + id + " id");
        }
    }
}
