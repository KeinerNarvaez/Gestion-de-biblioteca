package libraryManagement.test.controller;

import libraryManagement.test.dto.ReservationDto;
import libraryManagement.test.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public List<ReservationDto> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable Long id) {
        ReservationDto reservation = reservationService.getReservationById(id);
        return ResponseEntity.ok(reservation);
    }

    @PostMapping
    public ResponseEntity<ReservationDto> createReservation(@Valid @RequestBody ReservationDto reservationDto) {
        ReservationDto createdReservation = reservationService.createReservation(reservationDto);
        return ResponseEntity.ok(createdReservation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDto> updateReservation(@PathVariable Long id, @Valid @RequestBody ReservationDto reservationDto) {
        ReservationDto updatedReservation = reservationService.updateReservation(id, reservationDto);
        return ResponseEntity.ok(updatedReservation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<ReservationDto> activateReservation(@PathVariable Long id) {
        ReservationDto activatedReservation = reservationService.activateReservation(id);
        return ResponseEntity.ok(activatedReservation);
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<ReservationDto> cancelReservation(@PathVariable Long id) {
        ReservationDto cancelledReservation = reservationService.cancelReservation(id);
        return ResponseEntity.ok(cancelledReservation);
    }

    @PutMapping("/{id}/fulfill")
    public ResponseEntity<ReservationDto> fulfillReservation(@PathVariable Long id) {
        ReservationDto fulfilledReservation = reservationService.fulfillReservation(id);
        return ResponseEntity.ok(fulfilledReservation);
    }
}