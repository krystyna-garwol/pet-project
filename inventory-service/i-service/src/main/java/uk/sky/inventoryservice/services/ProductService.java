package uk.sky.inventoryservice.services;

import org.springframework.stereotype.Service;
import uk.sky.inventoryservice.repositories.ProductRepository;

import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Integer> getStock(String productId) {
        return productRepository.findStockByProductId(productId);
    }
}
