package com.chadwick.propertymanagement.service;

import com.chadwick.propertymanagement.dto.UserDTO;

public interface UserService {

    public UserDTO register(UserDTO userDTO);

    public UserDTO login(String userEmail, String userPassword);
}
