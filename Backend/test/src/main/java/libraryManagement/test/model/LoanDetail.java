package libraryManagement.test.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "loan_details")
@Data
public class LoanDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detailId;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private Integer quantity;
}