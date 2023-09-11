package com.example.boutique.controllers;

import com.example.boutique.entities.Product;
import com.example.boutique.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {
    @Autowired
    private ProductController productController;
    @Autowired
    private ProductService productService;

    //Afficher tous les tutoriels
    @GetMapping("/products")
    public String searchAllProducts(Model model) {
        return  productService.findAllProducts(model);
    }

    //Deux méthodes conjointes pour afficher une page html, récupérer les données du formulaire
    //créer le formulaire et retourner la liste de formulaires
    @GetMapping("/createform")
    public String showForm(Model model) {
        return productService.showForm(model);
    }
    @PostMapping("/createform")
    public String submitForm(@ModelAttribute Product product, Model model) {
        return productService.submitForm(product, model);
    }
}
