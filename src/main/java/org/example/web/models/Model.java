package org.example.web.models;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Model extends TimeEntity {

    @OneToMany(mappedBy = "model", targetEntity = Offer.class, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Offer> offers;

    private String name;

    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

    public enum CategoryType {
        CAR(0),
        Buss(10),
        Truck(20),
        Motorcycle(30);

        final int num;

        CategoryType(int num) {
            this.num = num;
        }

    }

    ;

    private String imageURL;

    private int startYear;

    private int endYear;

    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JoinColumn(name = "brand_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Brand brand;

    public Model(String id, LocalDate created, LocalDate modified, List<Offer> offers, String name, CategoryType categoryType, String imageURL, int startYear, int endYear, Brand brand) {
        super(id, created, modified);
        this.offers = offers;
        this.name = name;
        this.categoryType = categoryType;
        this.imageURL = imageURL;
        this.startYear = startYear;
        this.endYear = endYear;
        this.brand = brand;
    }

    public Model() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    @Override
    public String toString() {
        return "Model{" +
                "offers=" + offers +
                ", name='" + name + '\'' +
                ", categoryType=" + categoryType +
                ", imageURL='" + imageURL + '\'' +
                ", startYear=" + startYear +
                ", endYear=" + endYear +
                ", brand=" + brand +
                '}';
    }
}