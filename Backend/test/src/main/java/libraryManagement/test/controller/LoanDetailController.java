package libraryManagement.test.controller;

import libraryManagement.test.dto.LoanDetailDto;
import libraryManagement.test.service.LoanDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/loan-details")
public class LoanDetailController {

    @Autowired
    private LoanDetailService loanDetailService;

    @GetMapping
    public List<LoanDetailDto> getAllLoanDetails() {
        return loanDetailService.getAllLoanDetails();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanDetailDto> getLoanDetailById(@PathVariable Long id) {
        LoanDetailDto loanDetail = loanDetailService.getLoanDetailById(id);
        return ResponseEntity.ok(loanDetail);
    }

    @PostMapping
    public ResponseEntity<LoanDetailDto> createLoanDetail(@Valid @RequestBody LoanDetailDto loanDetailDto) {
        LoanDetailDto createdLoanDetail = loanDetailService.createLoanDetail(loanDetailDto);
        return ResponseEntity.ok(createdLoanDetail);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoanDetailDto> updateLoanDetail(@PathVariable Long id, @Valid @RequestBody LoanDetailDto loanDetailDto) {
        LoanDetailDto updatedLoanDetail = loanDetailService.updateLoanDetail(id, loanDetailDto);
        return ResponseEntity.ok(updatedLoanDetail);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoanDetail(@PathVariable Long id) {
        loanDetailService.deleteLoanDetail(id);
        return ResponseEntity.noContent().build();
    }
}