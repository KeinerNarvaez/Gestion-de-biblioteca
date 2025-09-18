package libraryManagement.test.service;

import libraryManagement.test.dto.LoanDetailDto;
import libraryManagement.test.model.Book;
import libraryManagement.test.model.Loan;
import libraryManagement.test.model.LoanDetail;
import libraryManagement.test.repository.BookRepository;
import libraryManagement.test.repository.LoanDetailRepository;
import libraryManagement.test.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanDetailService {

    @Autowired
    private LoanDetailRepository loanDetailRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<LoanDetailDto> getAllLoanDetails() {
        return loanDetailRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public LoanDetailDto getLoanDetailById(Long id) {
        LoanDetail loanDetail = loanDetailRepository.findById(id).orElseThrow(() -> new RuntimeException("LoanDetail not found"));
        return convertToDto(loanDetail);
    }

    public LoanDetailDto createLoanDetail(LoanDetailDto loanDetailDto) {
        LoanDetail loanDetail = convertToEntity(loanDetailDto);
        LoanDetail savedLoanDetail = loanDetailRepository.save(loanDetail);
        return convertToDto(savedLoanDetail);
    }

    public LoanDetailDto updateLoanDetail(Long id, LoanDetailDto loanDetailDto) {
        LoanDetail loanDetail = loanDetailRepository.findById(id).orElseThrow(() -> new RuntimeException("LoanDetail not found"));
        loanDetail.setLoan(loanRepository.findById(loanDetailDto.getLoanId()).orElseThrow(() -> new RuntimeException("Loan not found")));
        loanDetail.setBook(bookRepository.findById(loanDetailDto.getBookId()).orElseThrow(() -> new RuntimeException("Book not found")));
        loanDetail.setQuantity(loanDetailDto.getQuantity());
        LoanDetail updatedLoanDetail = loanDetailRepository.save(loanDetail);
        return convertToDto(updatedLoanDetail);
    }

    public void deleteLoanDetail(Long id) {
        loanDetailRepository.deleteById(id);
    }

    private LoanDetailDto convertToDto(LoanDetail loanDetail) {
        LoanDetailDto dto = new LoanDetailDto();
        dto.setDetailId(loanDetail.getDetailId());
        dto.setLoanId(loanDetail.getLoan().getLoanId());
        dto.setBookId(loanDetail.getBook().getBookId());
        dto.setQuantity(loanDetail.getQuantity());
        return dto;
    }

    private LoanDetail convertToEntity(LoanDetailDto dto) {
        LoanDetail loanDetail = new LoanDetail();
        loanDetail.setLoan(loanRepository.findById(dto.getLoanId()).orElseThrow(() -> new RuntimeException("Loan not found")));
        loanDetail.setBook(bookRepository.findById(dto.getBookId()).orElseThrow(() -> new RuntimeException("Book not found")));
        loanDetail.setQuantity(dto.getQuantity());
        return loanDetail;
    }
}