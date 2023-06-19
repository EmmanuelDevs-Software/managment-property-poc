package com.chadwick.propertymanagement.controller;
import com.chadwick.propertymanagement.dto.PropertyDTO;
import com.chadwick.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping("/connection")
    public String index() {
        return "connection available";
    }


    @PostMapping("/properties")
    public ResponseEntity<PropertyDTO> saveProperty(@RequestBody PropertyDTO propertyDTO) {
        propertyDTO = propertyService.saveProperty(propertyDTO);
        return new ResponseEntity<>(propertyDTO, HttpStatus.CREATED);
    }

    @GetMapping("/properties")
    public ResponseEntity<List<PropertyDTO>> getAllProperties() {
        List<PropertyDTO> propertyList = propertyService.getAllProperties();
        return new ResponseEntity<>(propertyList, HttpStatus.OK);
    }

    @PutMapping("/properties/{propertyId}")
    public ResponseEntity<PropertyDTO> updateProperty(@RequestBody PropertyDTO propertyDTO,@PathVariable Long propertyId) {
        propertyDTO = propertyService.updateProperty(propertyDTO, propertyId);
        return new ResponseEntity<>(propertyDTO, HttpStatus.OK);
    }

    @PatchMapping ("/properties/update-property/{propertyId}")
    public ResponseEntity<PropertyDTO> updatePropertyByPatch(@RequestBody PropertyDTO propertyDTO,@PathVariable Long propertyId) {
        propertyDTO = propertyService.updatePropertyByPatch(propertyDTO, propertyId);
        return new ResponseEntity<>(propertyDTO, HttpStatus.OK);
    }


@DeleteMapping("/properties/{propertyId}")
public ResponseEntity<Void> deleteProperty(@PathVariable Long propertyId) {
    propertyService.deleteProperty(propertyId);
    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
}

}
