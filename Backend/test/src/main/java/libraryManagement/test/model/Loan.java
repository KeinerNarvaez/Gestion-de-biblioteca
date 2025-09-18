package libraryManagement.test.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "loans")
@Data
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "librarian_id")
    private Librarian librarian;

    @Column(name = "loan_date")
    private LocalDate loanDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    private String status;
}