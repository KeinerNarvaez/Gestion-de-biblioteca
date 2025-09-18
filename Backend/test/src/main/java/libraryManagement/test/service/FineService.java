package libraryManagement.test.service;

import libraryManagement.test.dto.FineDto;
import libraryManagement.test.model.Fine;
import libraryManagement.test.model.User;
import libraryManagement.test.repository.FineRepository;
import libraryManagement.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FineService {

    @Autowired
    private FineRepository fineRepository;

    @Autowired
    private UserRepository userRepository;

    public List<FineDto> getAllFines() {
        return fineRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public FineDto getFineById(Long id) {
        Fine fine = fineRepository.findById(id).orElseThrow(() -> new RuntimeException("Fine not found"));
        return convertToDto(fine);
    }

    public FineDto createFine(FineDto fineDto) {
        Fine fine = convertToEntity(fineDto);
        Fine savedFine = fineRepository.save(fine);
        return convertToDto(savedFine);
    }

    public FineDto updateFine(Long id, FineDto fineDto) {
        Fine fine = fineRepository.findById(id).orElseThrow(() -> new RuntimeException("Fine not found"));
        fine.setUser(userRepository.findById(fineDto.getUserId()).orElseThrow(() -> new RuntimeException("User not found")));
        fine.setAmount(fineDto.getAmount());
        fine.setDescription(fineDto.getDescription());
        fine.setPaid(fineDto.getPaid());
        Fine updatedFine = fineRepository.save(fine);
        return convertToDto(updatedFine);
    }

    public void deleteFine(Long id) {
        fineRepository.deleteById(id);
    }

    // Custom method to assign fine to user
    public FineDto assignFine(Long userId, BigDecimal amount, String description) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Fine fine = new Fine();
        fine.setUser(user);
        fine.setAmount(amount);
        fine.setDescription(description);
        fine.setPaid(false);
        Fine savedFine = fineRepository.save(fine);
        return convertToDto(savedFine);
    }

    private FineDto convertToDto(Fine fine) {
        FineDto dto = new FineDto();
        dto.setFineId(fine.getFineId());
        dto.setUserId(fine.getUser().getUserId());
        dto.setAmount(fine.getAmount());
        dto.setDescription(fine.getDescription());
        dto.setPaid(fine.getPaid());
        return dto;
    }

    private Fine convertToEntity(FineDto dto) {
        Fine fine = new Fine();
        fine.setUser(userRepository.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User not found")));
        fine.setAmount(dto.getAmount());
        fine.setDescription(dto.getDescription());
        fine.setPaid(dto.getPaid());
        return fine;
    }
}