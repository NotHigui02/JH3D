package com.example.crud.product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "api/v1/products")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
   }

    @GetMapping
    public List<Product> getAllProducts() {
        return this.productService.getProducts();
    }


    @PostMapping
    public ResponseEntity<Object> addProduct(@RequestBody Product product) {
        return this.productService.addProduct(product);
    }

    @PutMapping
    public ResponseEntity<Object> updateProduct(@RequestBody Product product) {
        return this.productService.updateProduct(product);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteProduct(@RequestBody Product product) {
        return this.productService.deleteProduct(product);
    }
}






