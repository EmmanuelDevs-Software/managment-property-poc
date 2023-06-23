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
    private String description;
    private Double price;
    private String address;
    @ManyToOne//(fetch = FetchType.LAZY)//it will not fetch the user data while fetching property
    @JoinColumn(name = "USER_ID", nullable = false)
    private UserEntity userEntity;
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
