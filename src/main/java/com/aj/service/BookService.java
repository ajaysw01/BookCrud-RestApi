package com.aj.service;

import java.util.List;
import java.util.Optional;

import com.aj.binding.BookDTO;
import com.aj.entity.Book;

public interface BookService {
	
	public Book createBook(BookDTO bookDTO);

	public List<Book> getAllBooks();

	public Optional<Book> getBookById(Long id);

	public Book updateBook(Long id, BookDTO bookDTO);

	public void deleteBook(Long id);

}
