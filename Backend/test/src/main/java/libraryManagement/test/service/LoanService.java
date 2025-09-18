package libraryManagement.test.service;

import libraryManagement.test.dto.LoanDto;
import libraryManagement.test.model.Librarian;
import libraryManagement.test.model.Loan;
import libraryManagement.test.model.User;
import libraryManagement.test.repository.LibrarianRepository;
import libraryManagement.test.repository.LoanRepository;
import libraryManagement.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LibrarianRepository librarianRepository;

    public List<LoanDto> getAllLoans() {
        return loanRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public LoanDto getLoanById(Long id) {
        Loan loan = loanRepository.findById(id).orElseThrow(() -> new RuntimeException("Loan not found"));
        return convertToDto(loan);
    }

    public LoanDto createLoan(LoanDto loanDto) {
        Loan loan = convertToEntity(loanDto);
        Loan savedLoan = loanRepository.save(loan);
        return convertToDto(savedLoan);
    }

    public LoanDto updateLoan(Long id, LoanDto loanDto) {
        Loan loan = loanRepository.findById(id).orElseThrow(() -> new RuntimeException("Loan not found"));
        loan.setUser(userRepository.findById(loanDto.getUserId()).orElseThrow(() -> new RuntimeException("User not found")));
        loan.setLibrarian(librarianRepository.findById(loanDto.getLibrarianId()).orElseThrow(() -> new RuntimeException("Librarian not found")));
        loan.setLoanDate(loanDto.getLoanDate());
        loan.setReturnDate(loanDto.getReturnDate());
        loan.setStatus(loanDto.getStatus());
        Loan updatedLoan = loanRepository.save(loan);
        return convertToDto(updatedLoan);
    }

    public void deleteLoan(Long id) {
        loanRepository.deleteById(id);
    }

    public List<LoanDto> getOverdueLoans() {
        LocalDate currentDate = LocalDate.now();
        return loanRepository.findOverdueLoans(currentDate).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private LoanDto convertToDto(Loan loan) {
        LoanDto dto = new LoanDto();
        dto.setLoanId(loan.getLoanId());
        dto.setUserId(loan.getUser().getUserId());
        dto.setLibrarianId(loan.getLibrarian().getLibrarianId());
        dto.setLoanDate(loan.getLoanDate());
        dto.setReturnDate(loan.getReturnDate());
        dto.setStatus(loan.getStatus());
        return dto;
    }

    private Loan convertToEntity(LoanDto dto) {
        Loan loan = new Loan();
        loan.setUser(userRepository.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User not found")));
        loan.setLibrarian(librarianRepository.findById(dto.getLibrarianId()).orElseThrow(() -> new RuntimeException("Librarian not found")));
        loan.setLoanDate(dto.getLoanDate());
        loan.setReturnDate(dto.getReturnDate());
        loan.setStatus(dto.getStatus());
        return loan;
    }
}