package libraryManagement.test.repository;

import libraryManagement.test.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    @Query("SELECT l FROM Loan l WHERE l.returnDate < :currentDate AND l.status = 'active'")
    List<Loan> findOverdueLoans(LocalDate currentDate);
}