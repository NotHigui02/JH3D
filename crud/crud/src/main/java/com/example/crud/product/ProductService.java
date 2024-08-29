package com.example.crud.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts(){
        return this.productRepository.findAll();
    }

    public ResponseEntity<Object> addProduct(Product product) {
        Optional<Product> res = productRepository.findByName(product.getName());
        HashMap<String, Object> data = new HashMap<>();

        if (res.isPresent()) {
            data.put("Warning: ", true);
            data.put("message: ", "product already exists");
            return new ResponseEntity<>(
                    data,
                    HttpStatus.CONFLICT
            );
        }

        productRepository.save(product);
        data.put("data: ", product);
        data.put("message: ", "product crated successfully");
        return new ResponseEntity<>(
                data,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> updateProduct(Product product) {
        Optional<Product> res = productRepository.findById(product.getId());
        HashMap<String, Object> data = new HashMap<>();

        if (res.isPresent()) {
            productRepository.save(product);
            data.put("data: ", product);
            data.put("message: ", "product updated successfully");
            return new ResponseEntity<>(
                    data,
                    HttpStatus.OK
            );
        }

        data.put("warning: ", true);
        data.put("message: ", "does not exist");
        return new ResponseEntity<>(
            data,
            HttpStatus.CONFLICT
        );
    }

    public ResponseEntity<Object> deleteProduct(Product product) {
        Optional<Product> res = productRepository.findById(product.getId());
        HashMap<String, Object> data = new HashMap<>();

        if (res.isPresent()) {
            productRepository.delete(product);
            data.put("data: ", null);
            data.put("message: ", "product deleted successfully");
            return new ResponseEntity<>(
                    data,
                    HttpStatus.OK
            );
        }

        data.put("warning: ", true);
        data.put("message: ", "does not exist");
        return new ResponseEntity<>(
                data,
                HttpStatus.CONFLICT
        );
    }
}
