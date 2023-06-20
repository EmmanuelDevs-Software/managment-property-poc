package com.chadwick.propertymanagement.repository;
import com.chadwick.propertymanagement.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /*
     * @Query("SELECT u FROM UserEntity u WHERE u.userEmail = ?1 AND u.userPassword = ?2")
     * findByUserEmailAndUserPassword is the query using jpa can change the and for or
     */
    Optional<UserEntity> findByUserEmailAndUserPassword(String userEmail, String userPassword);
    Optional<UserEntity> findByUserEmail(String userEmail);
}
