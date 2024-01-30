package com.example.ecommerce.Repository;

import com.example.ecommerce.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findUserByEmail(String email);
    Optional<UserEntity> findUserByUsername(String username);
    Boolean existsByEmail(String email);
}
