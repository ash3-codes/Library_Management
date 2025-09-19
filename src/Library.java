import java.util.*;
import java.util.stream.Collectors;

public class Library {
    private final Map<Integer, LibraryItem> items;
    private final List<BorrowingRecord> borrowingRecords;
    private final FineCalculator fineCalculator;

    public Library() {
        this(new DefaultFineCalculator(10.0)); // Default fine is 10 rs/day
    }

    public Library(FineCalculator fineCalculator) {
        if (fineCalculator == null) {
            throw new IllegalArgumentException("Fine calculator cannot be null");
        }

        this.items = new HashMap<>();
        this.borrowingRecords = new ArrayList<>();
        this.fineCalculator = fineCalculator;
    }

    public void addItem(LibraryItem item) throws IllegalArgumentException {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }

        if (items.containsKey(item.getItemId())) {
            throw new IllegalArgumentException("Item with ID " + item.getItemId() + " already exists");
        }

        items.put(item.getItemId(), item);
    }

    public LibraryItem getItem(int itemId) throws ItemNotFoundException {
        LibraryItem item = items.get(itemId);
        if (item == null) {
            throw new ItemNotFoundException("Item with ID " + itemId + " not found");
        }
        return item;
    }

    public BorrowingRecord borrowItem(int itemId, String borrowerName, String borrowDuration)
            throws ItemNotFoundException, ItemNotAvailableException, IllegalArgumentException {
        LibraryItem item = getItem(itemId);

        if (!item.isAvailable()) {
            throw new ItemNotAvailableException("Item with ID " + itemId + " is not available for borrowing");
        }

        BorrowingRecord record = new BorrowingRecord(item, borrowerName, borrowDuration, fineCalculator);
        item.borrow();
        borrowingRecords.add(record);
        return record;
    }

    public BorrowingRecord returnItem(int itemId) throws ItemNotFoundException, ItemNotBorrowedException {
        LibraryItem item = getItem(itemId);

        BorrowingRecord record = borrowingRecords.stream()
                .filter(r -> r.getItem().getItemId() == itemId && !r.isReturned())
                .findFirst()
                .orElseThrow(() -> new ItemNotBorrowedException("Item with ID " + itemId + " is not currently borrowed"));

        record.returnItem();
        return record;
    }

    public double calculateFine(int itemId) throws ItemNotFoundException, ItemNotBorrowedException {
        BorrowingRecord record = borrowingRecords.stream()
                .filter(r -> r.getItem().getItemId() == itemId && !r.isReturned())
                .findFirst()
                .orElseThrow(() -> new ItemNotBorrowedException("Item with ID " + itemId + " is not currently borrowed"));

        return record.calculateFine();
    }

    public List<LibraryItem> searchByType(String itemType) {
        if (itemType == null || itemType.trim().isEmpty()) {
            return Collections.emptyList();
        }

        return items.values().stream()
                .filter(item -> item.getItemType().equalsIgnoreCase(itemType))
                .collect(Collectors.toList());
    }

    public List<LibraryItem> searchByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            return Collections.emptyList();
        }

        String searchTitle = title.toLowerCase();
        return items.values().stream()
                .filter(item -> item.getTitle().toLowerCase().contains(searchTitle))
                .collect(Collectors.toList());
    }

    public List<LibraryItem> searchByAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            return Collections.emptyList();
        }

        String searchAuthor = author.toLowerCase();
        return items.values().stream()
                .filter(item -> item.getAuthor().toLowerCase().contains(searchAuthor))
                .collect(Collectors.toList());
    }

    public List<BorrowingRecord> getBorrowingRecords() {
        return new ArrayList<>(borrowingRecords);
    }

    public List<BorrowingRecord> getOverdueRecords() {
        return borrowingRecords.stream()
                .filter(BorrowingRecord::isOverdue)
                .collect(Collectors.toList());
    }

    public List<BorrowingRecord> getActiveBorrowingRecords() {
        return borrowingRecords.stream()
                .filter(r -> !r.isReturned())
                .collect(Collectors.toList());
    }

    public static class ItemNotFoundException extends Exception {
        public ItemNotFoundException(String message) {
            super(message);
        }
    }

    public static class ItemNotAvailableException extends Exception {
        public ItemNotAvailableException(String message) {
            super(message);
        }
    }

    public static class ItemNotBorrowedException extends Exception {
        public ItemNotBorrowedException(String message) {
            super(message);
        }
    }
}