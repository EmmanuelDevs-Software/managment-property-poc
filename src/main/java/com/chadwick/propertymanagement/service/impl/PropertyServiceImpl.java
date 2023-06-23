package com.chadwick.propertymanagement.service.impl;

import com.chadwick.propertymanagement.converter.PropertyConverter;
import com.chadwick.propertymanagement.dto.PropertyDTO;
import com.chadwick.propertymanagement.entity.PropertyEntity;
import com.chadwick.propertymanagement.entity.UserEntity;
import com.chadwick.propertymanagement.exception.BusinessException;
import com.chadwick.propertymanagement.exception.ErrorModel;
import com.chadwick.propertymanagement.repository.PropertyRepository;
import com.chadwick.propertymanagement.repository.UserRepository;
import com.chadwick.propertymanagement.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Singleton
@RequiredArgsConstructor
@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyConverter propertyConverter;

    private final PropertyRepository propertyRepository;

    private final UserRepository userRepository;


    List<ErrorModel> errorModelList = new ArrayList<>();


    private String errorMssage = "Property not found for id: ";

    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {

        Optional<UserEntity> optUe = userRepository.findById(propertyDTO.getUserId());
        if(optUe.isPresent()) {
            PropertyEntity pe = propertyConverter.convertDTOtoEntity(propertyDTO);
            pe.setUserEntity(optUe.get());
            pe = propertyRepository.save(pe);

            propertyDTO = propertyConverter.convertEntityToDTO(pe);
        }else{
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("USER_ID_NOT_EXIST");
            errorModel.setMessage("User does not exist");
            errorModelList.add(errorModel);

            throw new BusinessException(errorModelList);
        }
        return propertyDTO;
    }

    @Override
    public List<PropertyDTO> getAllProperties() {

        //System.out.println("Inside service "+dummy);
        //System.out.println("Inside service "+dbUrl);
        List<PropertyEntity> listOfProps = (List<PropertyEntity>)propertyRepository.findAll();
        List<PropertyDTO> propList = new ArrayList<>();

        for(PropertyEntity pe : listOfProps){
            PropertyDTO dto = propertyConverter.convertEntityToDTO(pe);
            propList.add(dto);
        }
        return propList;
    }

    @Override
    public List<PropertyDTO> getAllPropertiesForUser(Long userId) {
        List<PropertyEntity> listOfProps = (List<PropertyEntity>)propertyRepository.findAllByUserEntityId(userId);
        List<PropertyDTO> propList = new ArrayList<>();

        for(PropertyEntity pe : listOfProps){
            PropertyDTO dto = propertyConverter.convertEntityToDTO(pe);
            propList.add(dto);
        }
        return propList;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {

        Optional<PropertyEntity> optEn =  propertyRepository.findById(propertyId);
        PropertyDTO dto = null;
        if(optEn.isPresent()){

            PropertyEntity pe = optEn.get();//data from database
            pe.setTitle(propertyDTO.getTitle());
            pe.setAddress(propertyDTO.getAddress());
            pe.setPrice(propertyDTO.getPrice());
            pe.setDescription(propertyDTO.getDescription());
            dto = propertyConverter.convertEntityToDTO(pe);
            propertyRepository.save(pe);
        }
        return dto;
    }

    @Override
    public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optEn =  propertyRepository.findById(propertyId);
        PropertyDTO dto = null;
        if(optEn.isPresent()){
            PropertyEntity pe = optEn.get();//data from database
            pe.setDescription(propertyDTO.getDescription());
            dto = propertyConverter.convertEntityToDTO(pe);
            propertyRepository.save(pe);
        }
        return dto;
    }

    @Override
    public PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optEn =  propertyRepository.findById(propertyId);
        PropertyDTO dto = null;
        if(optEn.isPresent()){
            PropertyEntity pe = optEn.get();//data from database
            pe.setPrice(propertyDTO.getPrice());
            dto = propertyConverter.convertEntityToDTO(pe);
            propertyRepository.save(pe);
        }
        return dto;
    }

    @Override
    public void deleteProperty(Long propertyId) {
        propertyRepository.deleteById(propertyId);
    }
}