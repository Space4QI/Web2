package org.example.web.mappers;

import org.example.web.DTO.UserEntityDTO;
import org.example.web.models.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserEntityMapper {
    private final ModelMapper userEntityMap;

    @Autowired
    public UserEntityMapper(ModelMapper userEntityMap){
        this.userEntityMap = userEntityMap;
    }

    public UserEntityDTO toDTO (UserEntity input) {
        return userEntityMap.map(input, UserEntityDTO.class);
    }

    public UserEntity toEntity (UserEntityDTO input) {
        return userEntityMap.map(input, UserEntity.class);
    }
}
