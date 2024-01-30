package com.example.ecommerce.Repository;

import com.example.ecommerce.Entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
}
