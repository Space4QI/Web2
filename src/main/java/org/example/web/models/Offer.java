package org.example.web.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
public class Offer extends TimeEntity {

    private String description;

    public void setUserEntity(UserEntity userEntity) {
    }

    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    public enum EngineType {
        GASOLINE(0),
        DIESEL(10),
        ELECTRIC(20),
        HYBRID(30);

        int num;

        EngineType(int num) {
            this.num = num;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }

    private String imageURL;

    private int mileage;

    private double price;

    @Enumerated(EnumType.STRING)
    private TransmissionType transmissionType;

    public enum TransmissionType {
        MANUAL(0),
        AUTOMATIC(10);

        int num;

        TransmissionType(int num) {
            this.num = num;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }

    private LocalDate year;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "model_id")
    private Model model;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seller_id")
    private UserEntity seller;

    public Offer(String id, LocalDate created, LocalDate modified, String description, EngineType engineType, String imageURL, int mileage, double price, TransmissionType transmissionType, LocalDate year, Model model, UserEntity seller) {
        super(id, created, modified);
        this.description = description;
        this.engineType = engineType;
        this.imageURL = imageURL;
        this.mileage = mileage;
        this.price = price;
        this.transmissionType = transmissionType;
        this.year = year;
        this.model = model;
        this.seller = seller;
    }

    public Offer() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }


    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getYear() {
        return year;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public UserEntity getSeller() {
        return seller;
    }

    public void setSeller(UserEntity seller) {
        this.seller = seller;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    @Override
    public String toString() {
        return "Offer{" +
                ", description='" + description + '\'' +
                ", engineType=" + engineType +
                ", imageURL='" + imageURL + '\'' +
                ", mileage=" + mileage +
                ", price=" + price +
                ", transmissionType=" + transmissionType +
                ", year=" + year +
                ", model=" + model +
                ", seller=" + seller +
                '}';
    }
}
