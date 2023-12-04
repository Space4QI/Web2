package org.example.web.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDate;

@MappedSuperclass
public class TimeEntity extends BaseEntity {

    @Column(name = "created")
    private LocalDate created;

    @Column(name = "modified")
    private LocalDate modified;

    public TimeEntity(String id, LocalDate created, LocalDate modified) {
        super(id);
        this.created = created;
        this.modified = modified;
    }

    public TimeEntity() {

    }

    @PrePersist
    private void updateCreated() {
        created = LocalDate.now();
    }

    @PreUpdate
    private void updateModified() {
        modified = LocalDate.now();
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getModified() {
        return modified;
    }

    public void setModified(LocalDate modified) {
        this.modified = modified;
    }
}
