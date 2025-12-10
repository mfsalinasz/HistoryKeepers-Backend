package com.historykeepers.service;

import com.historykeepers.model.Product;
import com.historykeepers.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // LÓGICA DE NEGOCIO: Agregar dato curioso respetando capas
    public Product agregarDatoCurioso(Long id, String nuevoDato) {
        // 1. Buscamos el producto (usando el repositorio)
        return productRepository.findById(id)
                .map(producto -> {
                    // 2. Aplicamos la lógica (concatenar texto)
                    String datoActual = producto.getDatoCurioso();
                    if (datoActual == null) {
                        datoActual = "";
                    }
                    producto.setDatoCurioso(datoActual + "\n- " + nuevoDato);
                    
                    // 3. Guardamos los cambios
                    return productRepository.save(producto);
                })
                .orElse(null); // Si no existe, retornamos null
    }
}

