package com.historykeepers.config;

import com.historykeepers.model.Product;
import com.historykeepers.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(ProductRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                Product p1 = new Product();
                p1.setName("Jersey 1986 Argentina");
                p1.setDescription("Camiseta oficial autografiada por Maradona.");
                p1.setCategory("Jersey");
                p1.setPrice(new BigDecimal("1500.00"));
                p1.setStock(1);
                p1.setImages(List.of("https://placehold.co/600x800?text=Jersey+1986",
                        "https://placehold.co/600x800?text=Detail"));

                Product p2 = new Product();
                p2.setName("Balón Telstar 1970");
                p2.setDescription("Balón oficial del mundial México 70.");
                p2.setCategory("Balón");
                p2.setPrice(new BigDecimal("800.00"));
                p2.setStock(3);
                p2.setImages(List.of("https://placehold.co/600x800?text=Ball+1970"));

                repository.saveAll(List.of(p1, p2));
                System.out.println("Database seeded with sample products.");
            }
        };
    }
}
