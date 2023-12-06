package org.example.web.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Brand extends TimeEntity {
    @OneToMany(mappedBy = "brand", targetEntity = Model.class, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<Model> models;

    private String name;

    public Brand(String id, LocalDate created, LocalDate modified, List<Model> models, String name) {
        super(id, created, modified);
        this.models = models;
        this.name = name;
    }

    public Brand() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "brand", cascade = CascadeType.REMOVE)
    public List<Model> getModels() {
        return models;
    }


    public void setModels(List<Model> models) {
        this.models = models;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "models=" + models +
                ", name='" + name + '\'' +
                '}';
    }
}
