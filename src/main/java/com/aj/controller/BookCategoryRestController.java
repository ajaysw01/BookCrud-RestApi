package com.aj.controller;

import com.aj.binding.BookCategoryDTO;
import com.aj.entity.BookCategory;
import com.aj.service.BookCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Book Category", description = "Book Category management APIs")
public class BookCategoryRestController {

    @Autowired
    private BookCategoryService bookCategoryService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(summary = "Create a new book category", description = "Creates a new book category entry")
    @ApiResponse(responseCode = "201", description = "Category created successfully", 
                 content = @Content(schema = @Schema(implementation = BookCategory.class)))
    public ResponseEntity<BookCategory> createCategory(@Valid @RequestBody BookCategoryDTO bookCategoryDTO) {
        BookCategory createdCategory = bookCategoryService.createCategory(bookCategoryDTO);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @GetMapping(produces = "application/json")
    @Operation(summary = "Get all book categories", description = "Retrieves a list of all book categories")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of categories", 
                 content = @Content(schema = @Schema(implementation = BookCategory.class)))
    public ResponseEntity<List<BookCategory>> getAllCategories() {
        List<BookCategory> categories = bookCategoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Get a book category by ID", description = "Retrieves a book category by its ID")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of category", 
                 content = @Content(schema = @Schema(implementation = BookCategory.class)))
    @ApiResponse(responseCode = "404", description = "Category not found")
    public ResponseEntity<BookCategory> getCategoryById(@Parameter(description = "ID of the category to retrieve") @PathVariable Long id) {
        return bookCategoryService.getCategoryById(id)
            .map(category -> new ResponseEntity<>(category, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Update a book category", description = "Updates an existing book category's information")
    @ApiResponse(responseCode = "200", description = "Category updated successfully", 
                 content = @Content(schema = @Schema(implementation = BookCategory.class)))
    @ApiResponse(responseCode = "404", description = "Category not found")
    public ResponseEntity<BookCategory> updateCategory(@Parameter(description = "ID of the category to update") @PathVariable Long id, 
                                                       @Valid @RequestBody BookCategoryDTO bookCategoryDTO) {
        BookCategory updatedCategory = bookCategoryService.updateCategory(id, bookCategoryDTO);
        if (updatedCategory != null) {
            return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Delete a book category", description = "Deletes a book category by its ID")
    @ApiResponse(responseCode = "204", description = "Category successfully deleted")
    @ApiResponse(responseCode = "404", description = "Category not found")
    public ResponseEntity<Void> deleteCategory(@Parameter(description = "ID of the category to delete") @PathVariable Long id) {
        bookCategoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}