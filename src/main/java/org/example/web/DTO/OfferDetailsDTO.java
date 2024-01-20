package org.example.web.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.web.models.Model;

import java.io.Serializable;

public class OfferDetailsDTO implements Serializable {

    private String uuid;
    private String description;
    private String imageUrl;
    private Integer mileage;
    private Double price;
    private String year;

    private UserEntityDTO seller;

    private ModelDTO model;

    private String transmissionType;

    private String engineType;

    private String modified;

    private String created;


    @NotEmpty(message = "Description must not be null or empty")
    @Size(min = 10, message = "Description must be at least 10 characters")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotEmpty(message = "imageURL cannot be null or empty")
    @Size(min = 10, message = "imageURL should be at least 10 characters long")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @NotNull(message = "Mileage must not be null or empty")
    @Min(value = 1, message = "Mileage must be a positive number")
    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    @NotNull(message = "Price must not be null or empty")
    @Min(value = 1, message = "Price must be a positive number")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @NotNull(message = "StartYear must be not null or empty")
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public UserEntityDTO getSeller() {
        return seller;
    }

    public void setSeller(UserEntityDTO seller) {
        this.seller = seller;
    }

    public ModelDTO getModel() {
        return model;
    }

    public void setModel(ModelDTO model) {
        this.model = model;
    }

    @NotNull(message = "Choose TransmissionType")
    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    @NotNull(message = "Choose EngineType")
    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
