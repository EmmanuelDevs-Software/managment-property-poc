package com.chadwick.propertymanagement.service;

import com.chadwick.propertymanagement.dto.PropertyDTO;

import java.util.List;

public interface PropertyService {

    public PropertyDTO saveProperty(PropertyDTO propertyDTO);
    public List<PropertyDTO> getAllProperties();
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId);
    public PropertyDTO updatePropertyByPatch(PropertyDTO propertyDTO, Long propertyId);

}
