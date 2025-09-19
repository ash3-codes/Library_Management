import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DefaultFineCalculator implements FineCalculator {
    private final double finePerDay;

    public DefaultFineCalculator(double finePerDay) {
        this.finePerDay = finePerDay;
    }

    @Override
    public double calculateFine(LocalDate dueDate, LocalDate returnDate) {
        if (returnDate == null || returnDate.isBefore(dueDate) || returnDate.isEqual(dueDate)) {
            return 0;
        }

        long daysOverdue = ChronoUnit.DAYS.between(dueDate, returnDate);
        return daysOverdue * finePerDay;
    }
}