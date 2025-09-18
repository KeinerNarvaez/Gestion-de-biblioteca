package libraryManagement.test.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ReservationDto {
    private Long reservationId;
    private Long userId;
    private Long bookId;
    private LocalDate reservationDate;
    private String status;
}