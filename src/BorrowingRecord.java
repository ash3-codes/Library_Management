import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BorrowingRecord {
    private final LibraryItem item;
    private final String borrowerName;
    private final LocalDate borrowDate;
    private final LocalDate dueDate;
    private LocalDate returnDate;
    private boolean returned;
    private final FineCalculator fineCalculator;

    public BorrowingRecord(LibraryItem item, String borrowerName, String borrowDuration) throws IllegalArgumentException {
        this(item, borrowerName, borrowDuration, new DefaultFineCalculator(10.0)); // Default fine is 10 rs/day
    }

    public BorrowingRecord(LibraryItem item, String borrowerName, String borrowDuration, FineCalculator fineCalculator)
            throws IllegalArgumentException {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        if (borrowerName == null || borrowerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Borrower name cannot be null or empty");
        }
        if (borrowDuration == null || borrowDuration.trim().isEmpty()) {
            throw new IllegalArgumentException("Borrow duration cannot be null or empty");
        }
        if (fineCalculator == null) {
            throw new IllegalArgumentException("Fine calculator cannot be null");
        }

        this.item = item;
        this.borrowerName = borrowerName;
        this.borrowDate = LocalDate.now();
        this.dueDate = calculateDueDate(borrowDuration);
        this.returned = false;
        this.fineCalculator = fineCalculator;
    }

    private LocalDate calculateDueDate(String borrowDuration) throws IllegalArgumentException {
        try {
            String[] parts = borrowDuration.toLowerCase().split(" ");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Invalid duration format. Use format like '7 days' or '2 weeks'");
            }

            int amount = Integer.parseInt(parts[0]);
            if (amount <= 0) {
                throw new IllegalArgumentException("Duration amount must be a positive number");
            }

            String unit = parts[1];

            switch (unit) {
                case "days":
                case "day":
                    return borrowDate.plusDays(amount);
                case "weeks":
                case "week":
                    return borrowDate.plusWeeks(amount);
                case "months":
                case "month":
                    return borrowDate.plusMonths(amount);
                default:
                    throw new IllegalArgumentException("Invalid time unit. Use days, weeks, or months");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid duration amount. Please enter a valid number");
        }
    }

    public double calculateFine() {
        if (returned) {
            return fineCalculator.calculateFine(dueDate, returnDate);
        }
        return fineCalculator.calculateFine(dueDate, LocalDate.now());
    }

    public boolean returnItem() {
        if (!returned) {
            this.returnDate = LocalDate.now();
            this.returned = true;
            item.returnItem();
            return true;
        }
        return false;
    }

    public LibraryItem getItem() { return item; }
    public String getBorrowerName() { return borrowerName; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getDueDate() { return dueDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public boolean isReturned() { return returned; }
    public boolean isOverdue() {
        if (returned) {
            return returnDate.isAfter(dueDate);
        }
        return LocalDate.now().isAfter(dueDate);
    }

    @Override
    public String toString() {
        return "BorrowingRecord [Item=" + item + ", Borrower=" + borrowerName +
                ", BorrowDate=" + borrowDate + ", DueDate=" + dueDate +
                (returned ? ", ReturnDate=" + returnDate : "") +
                ", Returned=" + returned + ", Fine=" + calculateFine() + "]";
    }
}