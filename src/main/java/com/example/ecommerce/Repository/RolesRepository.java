package com.example.ecommerce.Repository;

import com.example.ecommerce.Entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RolesRepository extends JpaRepository<RoleEntity,Integer> {
    Optional<RoleEntity> findByRole(String username);
}
