package org.example.web.DTO;

import java.time.LocalDate;

public class OfferDTO {
    private String description;
    private String imageUrl;
    private Integer mileage;
    private Double price;
    private LocalDate year;

    private UserEntityDTO seller;

    private ModelDTO model;

    private String transmissionType;

    private String engineType;


    public OfferDTO(String description, String imageUrl, Integer mileage, Double price, LocalDate year, UserEntityDTO seller, ModelDTO model, String transmissionType, String engineType) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

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

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    @Override
    public String toString() {
        return "OfferDTO{" +
                "description='" + description + '\'' +
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
