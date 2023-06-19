package com.chadwick.propertymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDTO {

    private Long id;
    private String title;
    private String description;
    private String owner;
    private String ownerName;
    private String ownerEmail;
    private String address;
    private Double price;

}
