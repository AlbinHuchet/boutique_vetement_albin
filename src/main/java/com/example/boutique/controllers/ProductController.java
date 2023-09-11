package com.example.boutique.controllers;

import com.example.boutique.entities.Product;
import com.example.boutique.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="*")
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/allproducts")
    public ResponseEntity<List<Product>> searchAllProducts() {
        return  productService.findAllProducts();
    }
    @PostMapping("/createproduct")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return  productService.createProduct(product);
    }
    @PutMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product updatedProduct) {
        return  productService.update(updatedProduct);
    }
    @DeleteMapping ("/delete/{productId}")
    public ResponseEntity <Product> delete (@PathVariable Long productId) {
        return productService.deleteById(productId);
    }

    @DeleteMapping ("/deleteall")
    public ResponseEntity <String> deleteAllTutorials() {
        return productService.deleteAllProducts();
    }
}
