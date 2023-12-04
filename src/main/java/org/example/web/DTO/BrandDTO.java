package org.example.web.DTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.example.web.utils.validation.UniqueBrandName;

import java.util.List;


public class BrandDTO {

    @UniqueBrandName
    private String name;
    private List<ModelDTO> models;

    public BrandDTO(String name, List<ModelDTO> models) {
        this.name = name;
        this.models = models;
    }

    public BrandDTO() {
    }


    @NotEmpty(message = "Brand name must not be null or empty")
    @Size(min = 1, max = 20, message = "Brand name must be between 1 and 20 characters.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ModelDTO> getModels() {
        return models;
    }

    public void setModels(List<ModelDTO> models) {
        this.models = models;
    }

    @Override
    public String toString() {
        return "BrandDTO{" +
                "name='" + name + '\'' +
                ", models=" + models +
                '}';
    }
}

