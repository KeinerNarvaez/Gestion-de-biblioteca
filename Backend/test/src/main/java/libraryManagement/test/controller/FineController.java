package libraryManagement.test.controller;

import libraryManagement.test.dto.FineDto;
import libraryManagement.test.service.FineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/fines")
public class FineController {

    @Autowired
    private FineService fineService;

    @GetMapping
    public List<FineDto> getAllFines() {
        return fineService.getAllFines();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FineDto> getFineById(@PathVariable Long id) {
        FineDto fine = fineService.getFineById(id);
        return ResponseEntity.ok(fine);
    }

    @PostMapping
    public ResponseEntity<FineDto> createFine(@Valid @RequestBody FineDto fineDto) {
        FineDto createdFine = fineService.createFine(fineDto);
        return ResponseEntity.ok(createdFine);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FineDto> updateFine(@PathVariable Long id, @Valid @RequestBody FineDto fineDto) {
        FineDto updatedFine = fineService.updateFine(id, fineDto);
        return ResponseEntity.ok(updatedFine);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFine(@PathVariable Long id) {
        fineService.deleteFine(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/assign")
    public ResponseEntity<FineDto> assignFine(@RequestParam Long userId, @RequestParam BigDecimal amount, @RequestParam String description) {
        FineDto assignedFine = fineService.assignFine(userId, amount, description);
        return ResponseEntity.ok(assignedFine);
    }
}