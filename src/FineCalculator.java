import java.time.LocalDate;

public interface FineCalculator {
    double calculateFine(LocalDate dueDate, LocalDate returnDate);
}