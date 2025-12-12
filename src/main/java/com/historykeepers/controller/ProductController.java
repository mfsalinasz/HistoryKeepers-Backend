package com.historykeepers.controller;

import com.historykeepers.model.Product;
import com.historykeepers.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    // 1. LEER TODOS
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // 2. LEER UNO
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. CREAR
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        // Aquí llegará el JSON con la URL de la imagen de Cloudinary
        return productService.saveProduct(product);
    }

    // 4. ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        return productService.getProductById(id)
                .map(existingProduct -> {
                    existingProduct.setName(productDetails.getName());
                    existingProduct.setCategory(productDetails.getCategory());
                    existingProduct.setDescription(productDetails.getDescription());
                    existingProduct.setImageUrl(productDetails.getImageUrl());
                    
                    Product updated = productService.saveProduct(existingProduct);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 5. ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (productService.getProductById(id).isPresent()) {
            productService.deleteProduct(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ENDPOINT: Solo recibe y responde
    @PostMapping("/{id}/curioso")
    public ResponseEntity<Product> agregarDatoCurioso(@PathVariable Long id, @RequestBody String nuevoDato) {
        // Delegamos el trabajo al servicio
        Product productoActualizado = productService.agregarDatoCurioso(id, nuevoDato);
        
        if (productoActualizado != null) {
            return ResponseEntity.ok(productoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}