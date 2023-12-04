package org.example.web.mappers;

import org.example.web.DTO.UserRoleDTO;
import org.example.web.models.UserRole;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRoleMapper {
    private final ModelMapper userRoleMap;

    @Autowired
    public UserRoleMapper(ModelMapper userRoleMap){
        this.userRoleMap = userRoleMap;
    }

    public UserRoleDTO toDTO (UserRole input) {
        return userRoleMap.map(input, UserRoleDTO.class);
    }

    public UserRole toEntity (UserRoleDTO input) {
        return userRoleMap.map(input, UserRole.class);
    }
}