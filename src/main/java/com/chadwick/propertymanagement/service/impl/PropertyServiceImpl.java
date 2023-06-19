package com.chadwick.propertymanagement.service.impl;

import com.chadwick.propertymanagement.converter.PropertyConverter;
import com.chadwick.propertymanagement.dto.PropertyDTO;
import com.chadwick.propertymanagement.entity.PropertyEntity;
import com.chadwick.propertymanagement.repository.PropertyRepository;
import com.chadwick.propertymanagement.service.PropertyService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        propertyDTO = propertyConverter.EntitytoDTO(propertyEntity);
        return propertyDTO;
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
       List<PropertyEntity> propertyEntityList = propertyRepository.findAll();
       List<PropertyDTO> propertyDTOList = new ArrayList<>();
       for (PropertyEntity propertyEntity : propertyEntityList) {
           PropertyDTO  propertyDTO = propertyConverter.EntitytoDTO(propertyEntity);
           propertyDTOList.add(propertyDTO);
       }
        return propertyDTOList;
    }

}
