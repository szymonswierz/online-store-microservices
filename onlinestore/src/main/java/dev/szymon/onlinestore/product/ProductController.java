package dev.szymon.onlinestore.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all")
    public String getAll(Model model) {
        List<ProductEntity> productEntities = productService.getAllProducts();
        model.addAttribute("products", productEntities);
        return "products";
    }

    @GetMapping("/categories/{categoryId}")
    public String getByCategoryId(@PathVariable int categoryId, Model model) {
        List<ProductEntity> productEntities = productService.getProductsByCategoryId(categoryId);
        model.addAttribute("products", productEntities);
        return "products";
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable int id, Model model) {
        ProductEntity productEntity = productService.getProductById(id);
        model.addAttribute("product", productEntity);
        return "product";
    }

    @GetMapping("/search")
    public String getSearchResult(@RequestParam String phrase, Model model) {
        List<ProductEntity> productEntities = productService.getProductsByNameContainingIgnoreCase(phrase);
        model.addAttribute("products", productEntities);
        return "products";
    }
}

