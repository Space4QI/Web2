package org.example.web.mappers;

import org.example.web.DTO.BrandDTO;
import org.example.web.models.Brand;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper (){

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
        TypeMap<BrandDTO, Brand> brandMapper = modelMapper.createTypeMap
                (BrandDTO.class, Brand.class);
        return modelMapper;
    }
}




//        modelMapper.getConfiguration()
//                .setFieldMatchingEnabled(true)
//                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
//        TypeMap<BrandDTO, Brand> brandMapper = modelMapper.createTypeMap(BrandDTO.class, Brand.class);

//        TypeMap<UserRoleDTO, UserRole> userRoleMapper = modelMapper.createTypeMap(UserRoleDTO.class, UserRole.class);
//        userRoleMapper.addMapping(UserRoleDTO::getId, UserRole::setId);
//
//        TypeMap<UserEntityDTO, UserEntity> userEntityMap = modelMapper.createTypeMap(UserEntityDTO.class, UserEntity.class);
//        userEntityMap.addMapping(UserEntityDTO::getUserRoleId, UserEntity::setUserRole);
