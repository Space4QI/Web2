package org.example.web.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class OfferDTO {

    private String id;
    private String description;
    private String imageUrl;
    private Integer mileage;
    private Double price;
    private LocalDate year;

    private UserEntityDTO seller;

    private ModelDTO model;

    private String transmissionType;

    private String engineType;


    public OfferDTO(String id, String description, String imageUrl, Integer mileage, Double price, LocalDate year, UserEntityDTO seller, ModelDTO model, String transmissionType, String engineType) {
        this.id = id;
        this.description = description;
        this.imageUrl = imageUrl;
        this.mileage = mileage;
        this.price = price;
        this.year = year;
        this.seller = seller;
        this.model = model;
        this.transmissionType = transmissionType;
        this.engineType = engineType;
    }

    public OfferDTO() {

    }

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
    public LocalDate getYear() {
        return year;
    }

    public void setYear(LocalDate year) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "OfferDTO{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", mileage=" + mileage +
                ", price=" + price +
                ", year=" + year +
                ", seller=" + seller +
                ", model=" + model +
                ", transmissionType='" + transmissionType + '\'' +
                ", engineType='" + engineType + '\'' +
                '}';
    }
}
