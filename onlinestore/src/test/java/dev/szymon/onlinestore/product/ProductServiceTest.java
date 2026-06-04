package dev.szymon.onlinestore.product;

import dev.szymon.onlinestore.exception.ProductNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void shouldReturnAllProducts() {

        ProductEntity product1 = new ProductEntity();
        product1.setId(1);
        product1.setName("Olej silnikowy 5W30 Castrol");
        product1.setPrice(159.99);
        product1.setDescription("Olej silnikowy syntetyczny do samochodów osobowych.");
        product1.setCategoryId(4);

        ProductEntity product2 = new ProductEntity();
        product2.setId(2);
        product2.setName("Gra planszowa Catan");
        product2.setPrice(149.99);
        product2.setDescription("Popularna gra strategiczna dla całej rodziny.");
        product2.setCategoryId(6);

        when(productRepository.findAll()).thenReturn(List.of(product1, product2));

        List<ProductEntity> result = productService.getAllProducts();

        assertEquals(2, result.size());
        assertEquals("Olej silnikowy 5W30 Castrol", result.get(0).getName());
        assertEquals("Gra planszowa Catan", result.get(1).getName());
    }

    @Test
    void shouldReturnProductsByCategoryId() {

        ProductEntity product1 = new ProductEntity();
        product1.setId(1);
        product1.setName("Smartfon Samsung Galaxy A55");
        product1.setPrice(1899.99);
        product1.setDescription("Smartfon z ekranem AMOLED, 128 GB pamięci i dobrym aparatem.");
        product1.setCategoryId(1);

        ProductEntity product2 = new ProductEntity();
        product2.setId(2);
        product2.setName("Laptop Lenovo IdeaPad 15");
        product2.setPrice(2999.00);
        product2.setDescription("Laptop do nauki, pracy biurowej i codziennego użytkowania.");
        product2.setCategoryId(1);

        when(productRepository.findByCategoryId(1)).thenReturn(List.of(product1, product2));

        List<ProductEntity> result = productService.getProductsByCategoryId(1);

        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getCategoryId());
        assertEquals(1, result.get(1).getCategoryId());

    }

    @Test
    void shouldReturnProduct() {

        ProductEntity product1 = new ProductEntity();
        product1.setId(1);
        product1.setName("Laptop Lenovo IdeaPad 15");
        product1.setPrice(2999.00);
        product1.setDescription("Laptop do nauki, pracy biurowej i codziennego użytkowania.");
        product1.setCategoryId(1);

        when(productRepository.findById(1)).thenReturn(Optional.of(product1));

        ProductEntity result = productService.getProductById(1);

        assertEquals(1, result.getId());
        assertEquals("Laptop Lenovo IdeaPad 15", result.getName());
    }

    @Test
    void shouldThrowExceptionWhenProductDoesNotExist() {

        when(productRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(1));
    }

    @Test
    void shouldReturnProductsByNameContainingIgnoreCase() {

        ProductEntity product1 = new ProductEntity();
        product1.setId(1);
        product1.setName("Smartfon Samsung Galaxy A55");
        product1.setPrice(1899.99);
        product1.setDescription("Smartfon z ekranem AMOLED, 128 GB pamięci i dobrym aparatem.");
        product1.setCategoryId(1);

        when(productRepository.findByNameContainingIgnoreCase("Smartfon")).thenReturn(List.of(product1));

        List<ProductEntity> result = productService.getProductsByNameContainingIgnoreCase("Smartfon");

        assertEquals(1, result.size());
        assertEquals("Smartfon Samsung Galaxy A55", result.get(0).getName());
    }

}
