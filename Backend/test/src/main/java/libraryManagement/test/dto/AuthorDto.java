package libraryManagement.test.dto;

import lombok.Data;

@Data
public class AuthorDto {
    private Long authorId;
    private String name;
    private String nationality;
}