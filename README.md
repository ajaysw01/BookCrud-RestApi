
#Book Crud Rest Api 
## Overview
This project provides RESTful APIs for managing books and book categories using Spring Boot.

## Technologies Used
- Java
- Spring Boot
- Swagger for API documentation
- Lombok for reducing boilerplate code

## Controllers

### BookRestController
- **Endpoint**: `/api/books`
  
#### Endpoints:
- **POST `/api/books`**
  - Creates a new book entry.
  - Request body should be a JSON object representing a `BookDTO`.
  
- **GET `/api/books`**
  - Retrieves a list of all books.
  
- **GET `/api/books/{id}`**
  - Retrieves a specific book by its ID.
  
- **PUT `/api/books/{id}`**
  - Updates an existing book's information.
  - Request body should be a JSON object representing a `BookDTO`.
  
- **DELETE `/api/books/{id}`**
  - Deletes a book by its ID.

### BookCategoryRestController
- **Endpoint**: `/api/categories`

#### Endpoints:
- **POST `/api/categories`**
  - Creates a new book category entry.
  - Request body should be a JSON object representing a `BookCategoryDTO`.
  
- **GET `/api/categories`**
  - Retrieves a list of all book categories.
  
- **GET `/api/categories/{id}`**
  - Retrieves a specific book category by its ID.
  
- **PUT `/api/categories/{id}`**
  - Updates an existing book category's information.
  - Request body should be a JSON object representing a `BookCategoryDTO`.
  
- **DELETE `/api/categories/{id}`**
  - Deletes a book category by its ID.

## Swagger Documentation
- Swagger UI is integrated to provide interactive API documentation.
- Access Swagger UI at `http://localhost:9091/swagger-ui/index.html`.
