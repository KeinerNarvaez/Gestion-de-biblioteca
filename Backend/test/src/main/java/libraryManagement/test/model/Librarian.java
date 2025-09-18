package libraryManagement.test.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "librarians")
@Data
public class Librarian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long librarianId;

    private String name;
    private String email;
    private String phone;

    @Column(name = "hire_date")
    private LocalDate hireDate;
}