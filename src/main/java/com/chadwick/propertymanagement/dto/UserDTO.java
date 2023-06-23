package com.chadwick.propertymanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    private Long id;
    private String userName;
    @NotNull(message = "Owner email is mandatory")
    @NotEmpty(message = "Owner email cannot be empty")
    @Size(min = 5, max = 50, message = "owner email must be between 5 and 50 characters")
    private String userEmail;
    private String userPhone;
    @NotNull(message = "Password cannot be null")
    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 4, max = 50, message = "owner email must be between 4 and 50 characters")
    private String userPassword;

    private String houseNo;
    private String street;
    private String city;
    private String postalCode;
    private String country;
}
