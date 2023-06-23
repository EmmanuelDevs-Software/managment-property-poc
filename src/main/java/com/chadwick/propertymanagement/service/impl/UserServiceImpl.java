package com.chadwick.propertymanagement.service.impl;

import com.chadwick.propertymanagement.converter.UserConverter;
import com.chadwick.propertymanagement.dto.UserDTO;
import com.chadwick.propertymanagement.entity.AddressEntity;
import com.chadwick.propertymanagement.entity.UserEntity;
import com.chadwick.propertymanagement.exception.BusinessException;
import com.chadwick.propertymanagement.exception.ErrorModel;
import com.chadwick.propertymanagement.repository.AddressRepository;
import com.chadwick.propertymanagement.repository.UserRepository;
import com.chadwick.propertymanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final UserConverter userConverter;


    @Override
    public UserDTO register(UserDTO userDTO) {

        Optional<UserEntity> optUe = userRepository.findByUserEmail(userDTO.getUserEmail());
        if(optUe.isPresent()){
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("EMAIL_ALREADY_EXIST");
            errorModel.setMessage("The Email With Which You Are Trying To Register Already Exist!");
            errorModelList.add(errorModel);
            throw new BusinessException(errorModelList);
        }

        UserEntity userEntity = userConverter.convertDTOtoEntity(userDTO);
        userEntity = userRepository.save(userEntity);

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setHouseNo(userDTO.getHouseNo());
        addressEntity.setCity(userDTO.getCity());
        addressEntity.setPostalCode(userDTO.getPostalCode());
        addressEntity.setStreet(userDTO.getStreet());
        addressEntity.setCountry(userDTO.getCountry());
        addressEntity.setUserEntity(userEntity);

        addressRepository.save(addressEntity);

        userDTO = userConverter.convertEntitytoDTO(userEntity);

        return userDTO;
    }

    @Override
    public UserDTO login(String email, String password) {
        UserDTO userDTO = null;
        Optional<UserEntity> optionalUserEntity = userRepository.findByUserEmailAndUserPassword(email, password);

        if(optionalUserEntity.isPresent()){
            userDTO = userConverter.convertEntitytoDTO(optionalUserEntity.get());
        }else{

            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("INVALID_LOGIN");
            errorModel.setMessage("Incorrect Email or Password");
            errorModelList.add(errorModel);

            throw new BusinessException(errorModelList);
        }
        return userDTO;
    }
}
