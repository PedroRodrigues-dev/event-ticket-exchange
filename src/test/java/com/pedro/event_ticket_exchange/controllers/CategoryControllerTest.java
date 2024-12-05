package com.pedro.event_ticket_exchange.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.pedro.event_ticket_exchange.entities.Category;
import com.pedro.event_ticket_exchange.services.CategoryService;

@SpringBootTest
class CategoryControllerTest {

    @Autowired
    private CategoryController categoryController;

    @MockitoBean
    private CategoryService categoryService;

    @Test
    void testGetAllCategoriesPaged() {
        List<Category> mockCategories = Arrays.asList(
                createCategory(1, "Group A", "Category 1", "Description 1"),
                createCategory(2, "Group B", "Category 2", "Description 2"));

        Page<Category> mockPage = new PageImpl<>(mockCategories, PageRequest.of(0, 10), mockCategories.size());

        when(categoryService.getAll(any(Pageable.class))).thenReturn(mockPage);

        Pageable pageable = PageRequest.of(0, 10);

        Page<Category> result = categoryController.getAllCategories(pageable);

        assertEquals(2, result.getContent().size());
        assertEquals("Category 1", result.getContent().get(0).getCategoryName());
        assertEquals("Description 2", result.getContent().get(1).getCategoryDescription());

        assertEquals(10, result.getSize());
        assertEquals(0, result.getNumber());
        assertEquals(1, result.getTotalPages());
    }

    @Test
    void testGetCategoryById_CategoryExists() {
        Category mockCategory = createCategory(1, "Group A", "Category 1", "Description 1");
        when(categoryService.getById(1)).thenReturn(Optional.of(mockCategory));

        ResponseEntity<Category> response = categoryController.getCategoryById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Category 1", response.getBody().getCategoryName());
        assertEquals("Group A", response.getBody().getCategoryGroup());
    }

    @Test
    void testGetCategoryById_CategoryNotFound() {
        when(categoryService.getById(1)).thenReturn(Optional.empty());

        ResponseEntity<Category> response = categoryController.getCategoryById(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    private Category createCategory(Integer categoryId, String categoryGroup, String categoryName,
            String categoryDescription) {
        Category category = new Category();
        category.setCategoryId(categoryId);
        category.setCategoryGroup(categoryGroup);
        category.setCategoryName(categoryName);
        category.setCategoryDescription(categoryDescription);

        return category;
    }
}
