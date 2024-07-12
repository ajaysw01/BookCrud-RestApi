package com.aj.service;

import com.aj.binding.BookCategoryDTO;
import com.aj.entity.BookCategory;
import com.aj.repo.BookCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookCategoryServiceImpl implements BookCategoryService{

    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    public BookCategory createCategory(BookCategoryDTO bookCategoryDTO) {
        BookCategory category = new BookCategory();
        category.setName(bookCategoryDTO.getName());
        return bookCategoryRepository.save(category);
    }

    public List<BookCategory> getAllCategories() {
        return bookCategoryRepository.findAll();
    }

    public Optional<BookCategory> getCategoryById(Long id) {
        return bookCategoryRepository.findById(id);
    }

    public BookCategory updateCategory(Long id, BookCategoryDTO bookCategoryDTO) {
        BookCategory category = bookCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        category.setName(bookCategoryDTO.getName());
        return bookCategoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        BookCategory category = bookCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        bookCategoryRepository.delete(category);
    }
}