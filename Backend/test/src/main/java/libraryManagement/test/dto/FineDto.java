package libraryManagement.test.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class FineDto {
    private Long fineId;
    private Long userId;
    private BigDecimal amount;
    private String description;
    private Boolean paid;
}