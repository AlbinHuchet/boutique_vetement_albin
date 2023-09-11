package com.example.boutique.repositories;

import com.example.boutique.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> findByProductDescription(String description);
    public List<Product> findProductsByProductDescriptionContaining(String productTerm);
    public List<Product> findByProductType(String productType);
    public Product findProductByProductName(String productName);

    public Product findProductByProductId(Long productId);


}
