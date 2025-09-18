package libraryManagement.test.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookDto {
    private Long bookId;

    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Author ID is required")
    private Long authorId;

    @NotNull(message = "Publisher ID is required")
    private Long publisherId;

    @NotNull(message = "Category ID is required")
    private Long categoryId;

    private Integer publicationYear;

    @Min(value = 0, message = "Total quantity must be positive")
    private Integer totalQuantity;

    @Min(value = 0, message = "Available quantity must be positive")
    private Integer availableQuantity;
}