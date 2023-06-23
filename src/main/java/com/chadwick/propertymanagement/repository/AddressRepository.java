package com.chadwick.propertymanagement.repository;

import com.chadwick.propertymanagement.entity.AddressEntity;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<AddressEntity, Long> {
}
