package dev.szymon.onlinestore.category;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void shouldReturnAllCategories() {
        CategoryEntity category1 = new CategoryEntity();
        category1.setId(1);
        category1.setName("Books");

        CategoryEntity category2 = new CategoryEntity();
        category2.setId(2);
        category2.setName("Flowers");

        when(categoryRepository.findAll()).thenReturn(List.of(category1, category2));

        List<CategoryEntity> result = categoryService.getAllCategories();

        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(2, result.get(1).getId());
        assertEquals("Books", result.get(0).getName());
        assertEquals("Flowers", result.get(1).getName());

    }

}
