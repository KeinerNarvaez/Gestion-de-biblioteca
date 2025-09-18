package libraryManagement.test.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class LoanDto {
    private Long loanId;
    private Long userId;
    private Long librarianId;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private String status;
}