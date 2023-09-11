package com.example.boutique.services;

import com.example.boutique.entities.Product;
import com.example.boutique.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepo;

    //Deux méthodes conjointes pour afficher une page html, récupérer les données du formulaire
    //créer le formulaire et retourner la liste de formulaires
    public String showForm(Model model) {
        model.addAttribute("product", new Product());
        return "create-product"; // Le nom de la vue du formulaire de création
    }

    public String submitForm(@ModelAttribute Product product, Model model) {
        model.addAttribute("product",  product);
        productRepo.save(product);
        return this.findAllProducts(model); // Le nom de la vue du formulaire de création
    }

    public String showUpdateForm(Model model) {
        model.addAttribute("product", new Product());
        System.out.println("création du modèle product");
        return "update-form"; // Le nom de la vue du formulaire de création
    }
    public String submitUpdateForm(@ModelAttribute Product product, Model model) {
        System.out.println("submit début");
        model.addAttribute("product",  product);
        System.out.println(product);
        Product p = productRepo.findProductByProductName(product.getProductName());
        System.out.println(p);
        if (p.getProductName() != null && p.getProductDescription() != null) {
            p.setProductName(product.getProductName());
            p.setProductDescription(product.getProductDescription());
            p.setProductType(product.getProductType());
            System.out.println(p + "après update");
            productRepo.save(p);
            return this.findAllProducts(model);        // Le nom de la vue du formulaire de création
        } else {
            return "index";
        }
    }
    public String showDeleteForm(Model model) {
        model.addAttribute("product", new Product());
        return "delete-form"; // Le nom de la vue du formulaire de création
    }
    public String submitDeleteForm(@ModelAttribute Product product, Model model) {
        model.addAttribute("product",  product);
        Product p = productRepo.findProductByProductName(product.getProductName());
        if (p.getProductName() != null && p.getProductDescription() != null) {
            productRepo.delete(p);
            return this.findAllProducts(model);        // Le nom de la vue du formulaire de création
        } else {
            return "index";
        }
    }
    public String showDeleteAllForm(Model model) {
        model.addAttribute("product", new Product());
        return "deleteall-form"; // Le nom de la vue du formulaire de création
    }
    public String submitDeleteAllForm(@ModelAttribute Product product, Model model) {
        model.addAttribute("product",  product);
        productRepo.deleteAll();
        return this.findAllProducts(model);        // Le nom de la vue du formulaire de création
    }

    public String findAllProducts(Model model) {
        List<Product> products = productRepo.findAll();
        model.addAttribute("products", products);
        return "products";
    }


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
