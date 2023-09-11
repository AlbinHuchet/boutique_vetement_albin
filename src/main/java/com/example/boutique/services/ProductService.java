package com.example.boutique.services;

import com.example.boutique.entities.Product;
import com.example.boutique.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepo;
//Je suis inialement parti sur des méthodes Rest Controller avant de me rappeler que pour utiliser thymeleaf il fallait du controller
    public ResponseEntity<List<Product>> findAllProducts() {
        List<Product> product = productRepo.findAll();
        if (product.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retourne un code NO CONTENT si l'objet est vide.
        } else {
            return ResponseEntity.ok(product); // Retourne l'objet demandé en JSON.
        }
    }

    public ResponseEntity<Product> searchByName(String productName) {
        Product product = productRepo.findProductByProductName(productName);
        if (product == null) {
            return ResponseEntity.noContent().build(); // Retourne un code NO CONTENT si l'objet est vide.
        } else {
            return ResponseEntity.ok(product); // Retourne l'objet demandé en JSON.
        }
    }
    public ResponseEntity<List<Product>> searchByType(String productType) {
        List<Product> products = productRepo.findByProductType(productType);
        if (products.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retourne un code NO CONTENT si l'objet est vide.
        } else {
            return ResponseEntity.ok(products); // Retourne l'objet demandé en JSON.
        }
    }
    public ResponseEntity<List<Product>> searchTutorialsByDescription(String productDescription) {
        List<Product> products = productRepo.findByProductDescription(productDescription);
        if (products.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retourne un code NO CONTENT si l'objet est vide.
        } else {
            return ResponseEntity.ok(products); // Retourne l'objet demandé en JSON.
        }
    }

    public ResponseEntity<List<Product>> searchTutorialsByTerm(String term) {
        List<Product> tutorials = productRepo.findProductsByProductDescriptionContaining(term);
        if (tutorials.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retourne un code NO CONTENT si l'objet est vide.
        } else {
            return ResponseEntity.ok(tutorials); // Retourne l'objet demandé en JSON.
        }
    }

    public ResponseEntity <Product> createProduct (Product product) {
        if (product.getProductName() != null && product.getProductDescription() != null) {
            productRepo.save(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
        }
    }

    public ResponseEntity <Product> update (Product updatedProduct) {
        Product p = productRepo.findProductByProductName(updatedProduct.getProductName());
        if (p.getProductName() != null && updatedProduct.getProductDescription() != null) {
            p.setProductName(updatedProduct.getProductName());
            p.setProductDescription(updatedProduct.getProductDescription());
            p.setProductType(updatedProduct.getProductType());
            productRepo.save(p);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    public ResponseEntity <Product> deleteById (Long productId) {
        Product p = productRepo.findProductByProductId(productId);
        if (p != null) {
            // Autres mises à jour possibles
            productRepo.delete(p);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    public ResponseEntity <String> deleteAllProducts() {
        List<Product> p = productRepo.findAll();
        if (p!=null) {
//            for (int i=0; i<p.size();i++) {
//                Product p2 = p.get(i);
//                productRepo.delete(p2);
//            }
            productRepo.deleteAll();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
