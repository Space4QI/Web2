package org.example.web.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ModelDTO {
    private String name;
    private String categoryType;
    private String imageUrl;
    private Integer startYear;
    private Integer endYear;

    private String brandName;

    public ModelDTO(String name, String categoryType, String imageUrl, Integer startYear, Integer endYear, String brandName) {
        this.name = name;
        this.categoryType = categoryType;
        this.imageUrl = imageUrl;
        this.startYear = startYear;
        this.endYear = endYear;
        this.brandName = brandName;
    }

    public ModelDTO() {

    }

    @NotEmpty(message = "Name cannot be null or empty")
    @Size(min = 2, message = "Name should be at least 2 characters long")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotEmpty(message = "imageURL cannot be null or empty")
    @Size(min = 10, message = "imageURL should be at least 10 characters long")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @NotNull(message = "StartYear must be not null or empty")
    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    @NotNull(message = "EndYear must be not null or empty")
    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @NotNull(message = "Choose categoryType")
    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

//    @Override
//    public String toString() {
//        return "ModelDTO{" +
//                ", name='" + name + '\'' +
//                ", categoryType='" + categoryType + '\'' +
//                ", imageUrl='" + imageUrl + '\'' +
//                ", startYear=" + startYear +
//                ", endYear=" + endYear +
//                ", brand=" + brandName +
//                '}';
//    }
}
