package libraryManagement.test.service;

import libraryManagement.test.dto.ReservationDto;
import libraryManagement.test.model.Book;
import libraryManagement.test.model.Reservation;
import libraryManagement.test.model.User;
import libraryManagement.test.repository.BookRepository;
import libraryManagement.test.repository.ReservationRepository;
import libraryManagement.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<ReservationDto> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ReservationDto getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new RuntimeException("Reservation not found"));
        return convertToDto(reservation);
    }

    public ReservationDto createReservation(ReservationDto reservationDto) {
        Reservation reservation = convertToEntity(reservationDto);
        Reservation savedReservation = reservationRepository.save(reservation);
        return convertToDto(savedReservation);
    }

    public ReservationDto updateReservation(Long id, ReservationDto reservationDto) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new RuntimeException("Reservation not found"));
        reservation.setUser(userRepository.findById(reservationDto.getUserId()).orElseThrow(() -> new RuntimeException("User not found")));
        reservation.setBook(bookRepository.findById(reservationDto.getBookId()).orElseThrow(() -> new RuntimeException("Book not found")));
        reservation.setReservationDate(reservationDto.getReservationDate());
        reservation.setStatus(reservationDto.getStatus());
        Reservation updatedReservation = reservationRepository.save(reservation);
        return convertToDto(updatedReservation);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    // Custom methods for managing reservations
    public ReservationDto activateReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new RuntimeException("Reservation not found"));
        reservation.setStatus("active");
        Reservation updated = reservationRepository.save(reservation);
        return convertToDto(updated);
    }

    public ReservationDto cancelReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new RuntimeException("Reservation not found"));
        reservation.setStatus("cancelled");
        Reservation updated = reservationRepository.save(reservation);
        return convertToDto(updated);
    }

    public ReservationDto fulfillReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new RuntimeException("Reservation not found"));
        reservation.setStatus("fulfilled");
        Reservation updated = reservationRepository.save(reservation);
        return convertToDto(updated);
    }

    private ReservationDto convertToDto(Reservation reservation) {
        ReservationDto dto = new ReservationDto();
        dto.setReservationId(reservation.getReservationId());
        dto.setUserId(reservation.getUser().getUserId());
        dto.setBookId(reservation.getBook().getBookId());
        dto.setReservationDate(reservation.getReservationDate());
        dto.setStatus(reservation.getStatus());
        return dto;
    }

    private Reservation convertToEntity(ReservationDto dto) {
        Reservation reservation = new Reservation();
        reservation.setUser(userRepository.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User not found")));
        reservation.setBook(bookRepository.findById(dto.getBookId()).orElseThrow(() -> new RuntimeException("Book not found")));
        reservation.setReservationDate(dto.getReservationDate());
        reservation.setStatus(dto.getStatus());
        return reservation;
    }
}