package com.chadwick.propertymanagement.service.impl;

import com.chadwick.propertymanagement.converter.UserConverter;
import com.chadwick.propertymanagement.dto.UserDTO;
import com.chadwick.propertymanagement.entity.UserEntity;
import com.chadwick.propertymanagement.repository.UserRepository;
import com.chadwick.propertymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDTO register(UserDTO userDTO) {
        UserEntity userEntity = userConverter.convertDTOtoEntity(userDTO);
        userEntity = userRepository.save(userEntity);
        userDTO = userConverter.convertEntitytoDTO(userEntity);
        return userDTO;
    }

    @Override
    public UserDTO login(String userEmail, String userPassword) {
        return null;
    }
}
