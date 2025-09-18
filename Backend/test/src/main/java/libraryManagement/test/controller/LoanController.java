package libraryManagement.test.controller;

import libraryManagement.test.dto.LoanDto;
import libraryManagement.test.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping
    public List<LoanDto> getAllLoans() {
        return loanService.getAllLoans();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanDto> getLoanById(@PathVariable Long id) {
        LoanDto loan = loanService.getLoanById(id);
        return ResponseEntity.ok(loan);
    }

    @PostMapping
    public ResponseEntity<LoanDto> createLoan(@Valid @RequestBody LoanDto loanDto) {
        LoanDto createdLoan = loanService.createLoan(loanDto);
        return ResponseEntity.ok(createdLoan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoanDto> updateLoan(@PathVariable Long id, @Valid @RequestBody LoanDto loanDto) {
        LoanDto updatedLoan = loanService.updateLoan(id, loanDto);
        return ResponseEntity.ok(updatedLoan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id) {
        loanService.deleteLoan(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/overdue")
    public List<LoanDto> getOverdueLoans() {
        return loanService.getOverdueLoans();
    }
}