package com.aj.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.aj.binding.BookDTO;
import com.aj.entity.Book;
import com.aj.service.BookService;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Book", description = "Book management APIs")
public class BookRestController {

    @Autowired
    private BookService bookService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(summary = "Create a new book", description = "Creates a new book entry")
    @ApiResponse(responseCode = "201", description = "Book created successfully", 
                 content = @Content(schema = @Schema(implementation = Book.class)))
    public ResponseEntity<Book> createBook(@Valid @RequestBody BookDTO bookDTO) {
        Book createdBook = bookService.createBook(bookDTO);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @GetMapping(produces = "application/json")
    @Operation(summary = "Get all books", description = "Retrieves a list of all books")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of books", 
                 content = @Content(schema = @Schema(implementation = Book.class)))
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Get a book by ID", description = "Retrieves a book by its ID")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of book", 
                 content = @Content(schema = @Schema(implementation = Book.class)))
    @ApiResponse(responseCode = "404", description = "Book not found")
    public ResponseEntity<Book> getBookById(@Parameter(description = "ID of the book to retrieve") @PathVariable Long id) {
        return bookService.getBookById(id)
            .map(book -> new ResponseEntity<>(book, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Update a book", description = "Updates an existing book's information")
    @ApiResponse(responseCode = "200", description = "Book updated successfully", 
                 content = @Content(schema = @Schema(implementation = Book.class)))
    @ApiResponse(responseCode = "404", description = "Book not found")
    public ResponseEntity<Book> updateBook(@Parameter(description = "ID of the book to update") @PathVariable Long id, 
                                           @Valid @RequestBody BookDTO bookDTO) {
        Book updatedBook = bookService.updateBook(id, bookDTO);
        if (updatedBook != null) {
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Delete a book", description = "Deletes a book by its ID")
    @ApiResponse(responseCode = "204", description = "Book successfully deleted")
    @ApiResponse(responseCode = "404", description = "Book not found")
    public ResponseEntity<Void> deleteBook(@Parameter(description = "ID of the book to delete") @PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}