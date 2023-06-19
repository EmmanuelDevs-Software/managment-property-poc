package com.chadwick.propertymanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "PROPERTY_TABLE")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PropertyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "PROPERTY_TITLE", nullable = false)
    private String title;
    @Column(name = "PROPERTY_DESCRIPTION", nullable = false)
    private String description;
    @Column(name = "PROPERTY_OWNER", nullable = false)
    private String owner;
    @Column(name = "PROPERTY_OWNER_NAME", nullable = false)
    private String ownerName;
    @Column(name = "PROPERTY_OWNER_EMAIL", nullable = false, unique = true)
    private String ownerEmail;
    @Column(name = "PROPERTY_ADDRESS", nullable = false)
    private String address;
    @Column(name = "PROPERTY_PRICE", nullable = false)
    private Double price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PropertyEntity that = (PropertyEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
