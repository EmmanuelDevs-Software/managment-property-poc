package com.chadwick.propertymanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "USER_TABLE")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "USER_NAME", nullable = false)
    private String userName;
    @Column(name = "USER_EMAIL", nullable = false, unique = true)
    private String userEmail;
    @Column(name = "USER_PHONE", nullable = false)
    private String userPhone;
    @Column(name = "USER_PASSWORD", nullable = false)
    private String userPassword;
}
