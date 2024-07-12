package com.aj.service;

import java.util.List;
import java.util.Optional;

import com.aj.binding.BookCategoryDTO;
import com.aj.entity.BookCategory;

public interface BookCategoryService {

	public BookCategory createCategory(BookCategoryDTO bookCategoryDTO);

	public List<BookCategory> getAllCategories();

	public Optional<BookCategory> getCategoryById(Long id);

	public BookCategory updateCategory(Long id, BookCategoryDTO bookCategoryDTO);

	public void deleteCategory(Long id);

}
