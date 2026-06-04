package dev.szymon.onlinestore.product;

import dev.szymon.onlinestore.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    public List<ProductEntity> getProductsByCategoryId(int categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    public ProductEntity getProductById(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product does not exist"));
    }

    public List<ProductEntity> getProductsByNameContainingIgnoreCase(String phrase) {
        return productRepository.findByNameContainingIgnoreCase(phrase);
    }

}
