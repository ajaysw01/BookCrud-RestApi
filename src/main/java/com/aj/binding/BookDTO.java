package com.aj.binding;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class BookDTO {

    @NotEmpty(message = "Title cannot be empty")
    private String title;

    @NotEmpty(message = "Author cannot be empty")
    private String author;

    @NotNull(message = "Published Date cannot be null")
    private LocalDate publishedDate;

    @NotNull(message = "Category ID cannot be null")
    private Long categoryId;

    @NotNull(message = "Price cannot be null")
    private Double price;
}
