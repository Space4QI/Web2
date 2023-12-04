package org.example.web.mappers;

import org.example.web.DTO.ModelDTO;
import org.example.web.models.Model;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModelsMapper {
    private final ModelMapper modelsMap;


    @Autowired
    public ModelsMapper(ModelMapper modelsMap){
        this.modelsMap = modelsMap;
    }

    public ModelDTO toDTO (Model input) {
        return modelsMap.map(input, ModelDTO.class);
    }

    public Model toEntity (ModelDTO input) {
        return modelsMap.map(input, Model.class);
    }
}
