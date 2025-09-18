package libraryManagement.test.dto;

import lombok.Data;

@Data
public class LoanDetailDto {
    private Long detailId;
    private Long loanId;
    private Long bookId;
    private Integer quantity;
}