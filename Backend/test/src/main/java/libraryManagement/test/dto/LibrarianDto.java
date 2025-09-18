package libraryManagement.test.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class LibrarianDto {
    private Long librarianId;
    private String name;
    private String email;
    private String phone;
    private LocalDate hireDate;
}