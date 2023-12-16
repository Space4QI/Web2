package org.example.web.services;

import org.example.web.DTO.BrandDTO;
import org.example.web.mappers.BrandMapper;
import org.example.web.models.Brand;
import org.example.web.repositories.BrandRepository;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheEvict;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@EnableCaching
public class BrandService {

    private final BrandRepository brandRepository;

    private final BrandMapper brandMapper;

    private final ModelMapper modelMapper;

    public BrandService(BrandRepository brandRepository, BrandMapper brandMapper, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.brandMapper = brandMapper;
        this.modelMapper = modelMapper;
    }

    @Cacheable("brand")
    public List<BrandDTO> getAllBrands() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return brandRepository.findAll()
                .stream()
                .map(brandMapper::toDTO)
                .collect(Collectors.toList());
    }

    public BrandDTO getBrandById(UUID id) {
        Brand brand = brandRepository.findById(id).orElseThrow(RuntimeException::new);
        return brandMapper.toDTO(brand);
    }

    @CacheEvict(cacheNames = "brand", allEntries = true)
    public BrandDTO addBrand(BrandDTO brandDTO) {
        Brand brand = modelMapper.map(brandDTO, Brand.class);
        Brand addBrand = brandRepository.saveAndFlush(brand);
        return modelMapper.map(addBrand, BrandDTO.class);
    }

    @CacheEvict(cacheNames = "brand", allEntries = true)
    public BrandDTO updateBrand(UUID id, BrandDTO updatedBrand) {
        return brandRepository.findById(id)
                .map(brand -> {
                    brand.setName(updatedBrand.getName());
                    return brandMapper.toDTO(brandRepository.saveAndFlush(brand));
                })
                .orElseThrow(() -> new IllegalArgumentException("dsd"));
    }

    public BrandDTO brandDetails(String brandName) {
        return modelMapper.map(brandRepository.findBrandByName(brandName).orElse(null), BrandDTO.class);
    }

    public BrandDTO saveBrand(BrandDTO brand) {
        return brandMapper.toDTO(brandRepository.saveAndFlush(brandMapper.toEntity(brand)));
    }

    @CacheEvict(cacheNames = "brand", allEntries = true)
    public void deleteBrand(String brandName) {
        try {
            brandRepository.deleteByName(brandName);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Error: there is no element with " + brandName + " name");
        }
    }
}
