package com.example.boutique.controllers;

import com.example.boutique.entities.Product;
import com.example.boutique.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {
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

    @GetMapping("/updateform")
    public String showUpdateForm(Model model) {
        return productService.showUpdateForm(model);
    }
    @PostMapping("/updateform")
    public String submitUpdateForm(@ModelAttribute Product product, Model model) {
        return productService.submitUpdateForm(product, model);
    }

    @GetMapping("/deleteform")
    public String showDeleteForm(Model model) {
        return productService.showDeleteForm(model);
    }
    @PostMapping("/deleteform")
    public String submitDeleteForm(@ModelAttribute Product product,Model model) {
        return productService.submitDeleteForm(product, model);
    }

    @GetMapping("/deleteallform")
    public String showDeleteAllForm(Model model) {
        return productService.showDeleteAllForm(model);
    }
    @PostMapping("/deleteallform")
    public String submitDeleteAllForm(@ModelAttribute Product product,Model model) {
        return productService.submitDeleteAllForm(product, model);
    }
}
