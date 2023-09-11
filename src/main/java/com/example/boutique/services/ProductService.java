package com.example.boutique.services;

import com.example.boutique.entities.Product;
import com.example.boutique.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepo;

    public List<Product> findAllProducts(){
        return null;
}


}
