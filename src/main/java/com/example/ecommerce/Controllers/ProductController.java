package com.example.ecommerce.Controllers;

import com.example.ecommerce.Dto.productionDto.DeleteProductDto;
import com.example.ecommerce.Dto.productionDto.EditProductDto;
import com.example.ecommerce.Dto.productionDto.PostProductDto;
import com.example.ecommerce.Entities.ProductEntity;
import com.example.ecommerce.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/product")
public class ProductController {
    private final ProductService productService;
    @Autowired
    private ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/get-Products")
    public List<ProductEntity> getProduct(){
        return productService.getProduct();
    }

    @PostMapping("/add-product")
    public void postProduct(@RequestBody PostProductDto postProductDto){
        productService.postProduct(postProductDto);
    }

    @DeleteMapping("/delete-product/{id}")
    public void deleteProduct(@PathVariable("id") DeleteProductDto deleteProductDto){
        productService.deleteProduct(deleteProductDto);
    }

    @PutMapping("/edit-product/{id}")
    public void putProduct(@PathVariable("id") Integer id,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String category,
                           @RequestParam(required = false) String desc,
                           @RequestParam(required = false) Integer price, EditProductDto editProductDto){
        productService.putProduct(editProductDto);

    }

    @GetMapping("/{id}")
    public ProductEntity getProductById(@PathVariable("id") Integer id){
        return productService.getProductById(id);
    }
}
