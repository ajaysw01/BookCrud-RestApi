package com.aj.binding;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class BookCategoryDTO {

    @NotEmpty(message = "Category name is required")
    private String name;
}
