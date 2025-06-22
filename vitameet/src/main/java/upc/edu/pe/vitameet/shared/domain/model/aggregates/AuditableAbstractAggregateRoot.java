package upc.edu.pe.vitameet.shared.domain.model.aggregates;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import upc.edu.pe.vitameet.shared.domain.model.entities.AuditableModel;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AuditableAbstractAggregateRoot<T> extends AuditableModel {

    @PrePersist
    public void prePersist() {
        this.setCreatedAt(LocalDateTime.now());
        this.setUpdatedAt(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate() {
        this.setUpdatedAt(LocalDateTime.now());
    }
}
