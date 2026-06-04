package dev.szymon.onlinestore.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/all")
    public String getAllCategories(Model model) {
        List<CategoryEntity> categoryEntities = categoryService.getAllCategories();
        model.addAttribute("categories", categoryEntities);
        return "categories";
    }

}
