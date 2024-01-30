package com.example.ecommerce.Services;

import com.example.ecommerce.Dto.productionDto.DeleteProductDto;
import com.example.ecommerce.Dto.productionDto.EditProductDto;
import com.example.ecommerce.Dto.productionDto.PostProductDto;
import com.example.ecommerce.Entities.ProductEntity;
import com.example.ecommerce.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<ProductEntity> getProduct(){
        return productRepository.findAll();
    }

    public ResponseEntity<String> postProduct(PostProductDto postProductDto) {
        try{
            ProductEntity addProduct = new ProductEntity();
            addProduct.setName(postProductDto.getName());
            addProduct.setCategory(postProductDto.getCategory());
            addProduct.setPrice(postProductDto.getPrice());
            addProduct.setDesc(postProductDto.getDescription());
            productRepository.save(addProduct);
            return new ResponseEntity<>("product added successfully", HttpStatus.OK);
        }catch(Exception e){
            throw new IllegalStateException("Error while adding product: "+e);
        }
    }

    public void deleteProduct(DeleteProductDto deleteProductDto) {
        boolean exists = productRepository.existsById(deleteProductDto.getId());
        if(!exists){
            throw new IllegalStateException("Student with id "+ deleteProductDto + "does not exists");
        }
        productRepository.deleteById(deleteProductDto.getId());
    }

    @Transactional
    public void putProduct(EditProductDto editProductDto) {
        ProductEntity product = productRepository.findById(editProductDto.getId())
                .orElseThrow(()-> new IllegalStateException("Product id "+editProductDto.getId()+" not exist"));
        if(editProductDto.getName() != null && !editProductDto.getName().isEmpty()){
            product.setName(editProductDto.getName());
        }
        if(editProductDto.getCategory() != null && !editProductDto.getCategory().isEmpty()){
            product.setName(editProductDto.getCategory());
        }
        if(editProductDto.getDescription() != null && editProductDto.getDescription().isEmpty()){
            product.setDesc(editProductDto.getDescription());
        }
        if(editProductDto.getPrice() != null && editProductDto.getPrice() >= 0){
            product.setPrice(editProductDto.getPrice());
        }
    }

    public ProductEntity getProductById(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new IllegalStateException("Product not found"));
    }
}
