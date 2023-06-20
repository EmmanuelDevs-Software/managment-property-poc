package com.chadwick.propertymanagement.converter;

import com.chadwick.propertymanagement.dto.UserDTO;
import com.chadwick.propertymanagement.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserEntity convertDTOtoEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userDTO.getUserName());
        userEntity.setUserEmail(userDTO.getUserEmail());
        userEntity.setUserPhone(userDTO.getUserPhone());
        userEntity.setUserPassword(userDTO.getUserPassword());
        return userEntity;
    }

    public UserDTO convertEntitytoDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setUserName(userEntity.getUserName());
        userDTO.setUserEmail(userEntity.getUserEmail());
        userDTO.setUserPhone(userEntity.getUserPhone());
        return userDTO;
    }
}
