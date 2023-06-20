package com.chadwick.propertymanagement.service.impl;

import com.chadwick.propertymanagement.converter.UserConverter;
import com.chadwick.propertymanagement.dto.UserDTO;
import com.chadwick.propertymanagement.entity.UserEntity;
import com.chadwick.propertymanagement.exception.BusinessException;
import com.chadwick.propertymanagement.exception.ErrorModel;
import com.chadwick.propertymanagement.repository.UserRepository;
import com.chadwick.propertymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDTO register(UserDTO userDTO) {
        Optional<UserEntity> optUserEntity = userRepository.findByUserEmail(userDTO.getUserEmail());
        if (optUserEntity.isPresent()) {
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("EMAIL_ALREADY_EXISTS");
            errorModel.setMessage("User already exists");
            errorModelList.add(errorModel);
            throw new BusinessException(errorModelList);
        } else {
            UserEntity userEntity = userConverter.convertDTOtoEntity(userDTO);
            userEntity = userRepository.save(userEntity);
            userDTO = userConverter.convertEntitytoDTO(userEntity);
            return userDTO;
        }
    }

    @Override
    public UserDTO login(String userEmail, String userPassword) {
        UserDTO userDTO = null;
        Optional<UserEntity> optUserEntity = userRepository.findByUserEmailAndUserPassword(userEmail, userPassword);
        if (optUserEntity.isPresent()) {
            userDTO = userConverter.convertEntitytoDTO(optUserEntity.get());
            return userDTO;
        } else {
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("404");
            errorModel.setMessage("User not found");
            errorModelList.add(errorModel);
            throw new BusinessException(errorModelList);
        }
    }
}
