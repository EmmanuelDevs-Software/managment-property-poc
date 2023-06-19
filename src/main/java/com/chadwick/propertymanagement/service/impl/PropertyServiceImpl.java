package com.chadwick.propertymanagement.service.impl;

import com.chadwick.propertymanagement.converter.PropertyConverter;
import com.chadwick.propertymanagement.dto.PropertyDTO;
import com.chadwick.propertymanagement.entity.PropertyEntity;
import com.chadwick.propertymanagement.repository.PropertyRepository;
import com.chadwick.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Singleton
@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private PropertyConverter propertyConverter;
    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {
        PropertyEntity propertyEntity = propertyConverter.convertDTOtoEntity(propertyDTO);
        propertyEntity = propertyRepository.save(propertyEntity);
        propertyDTO = propertyConverter.convertEntitytoDTO(propertyEntity);
        return propertyDTO;
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
       List<PropertyEntity> propertyEntityList = propertyRepository.findAll();
       List<PropertyDTO> propertyDTOList = new ArrayList<>();
       for (PropertyEntity propertyEntity : propertyEntityList) {
           PropertyDTO  propertyDTO = propertyConverter.convertEntitytoDTO(propertyEntity);
           propertyDTOList.add(propertyDTO);
       }
        return propertyDTOList;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optionalEntity = propertyRepository.findById(propertyId);
        PropertyDTO dto = null;
        if (optionalEntity.isPresent()) {
            PropertyEntity propertyEntity = optionalEntity.get(); // Data from the database
            propertyEntity.setTitle(propertyDTO.getTitle());
            propertyEntity.setDescription(propertyDTO.getDescription());
            propertyEntity.setOwner(propertyDTO.getOwner());
            propertyEntity.setOwnerName(propertyDTO.getOwnerName());
            propertyEntity.setOwnerEmail(propertyDTO.getOwnerEmail());
            propertyEntity.setAddress(propertyDTO.getAddress());
            propertyEntity.setPrice(propertyDTO.getPrice());
            propertyRepository.save(propertyEntity); // Save the updated entity
            dto = propertyConverter.convertEntitytoDTO(propertyEntity);
        } else {
            throw new IllegalArgumentException("Property not found for id: " + propertyId);
        }
        return dto;
    }


}
