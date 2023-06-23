package com.chadwick.propertymanagement.repository;

import com.chadwick.propertymanagement.entity.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<PropertyEntity, Long> {
    //@Query("SELECT p FROM PropertyEntity p WHERE p.userEntity.id = :userId AND p.title = :title")
    //List<PropertyEntity> findAllByUserEntityId(@Param("userId") Long userId, @Param("title") Long title)
    List<PropertyEntity> findAllByUserEntityId(@Param("userId") Long userId);
}
