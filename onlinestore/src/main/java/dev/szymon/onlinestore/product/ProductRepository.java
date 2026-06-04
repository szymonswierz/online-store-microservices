package dev.szymon.onlinestore.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    List<ProductEntity> findByCategoryId(int categoryId);
    List<ProductEntity> findByNameContainingIgnoreCase(String phrase);
}
