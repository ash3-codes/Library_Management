import java.time.LocalDate;

public abstract class LibraryItem {
    private final int itemId;
    private final String title;
    private final String author;
    private boolean available;

    public LibraryItem(int itemId, String title, String author) throws IllegalArgumentException {
        if (itemId <= 0) {
            throw new IllegalArgumentException("Item ID must be a positive integer");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be null or empty");
        }

        this.itemId = itemId;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public int getItemId() { return itemId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return available; }

    protected void setAvailable(boolean available) {
        this.available = available;
    }

    public abstract String getItemType();

    public boolean borrow() {
        if (available) {
            setAvailable(false);
            return true;
        }
        return false;
    }

    public boolean returnItem() {
        if (!available) {
            setAvailable(true);
            return true;
        }
        return false;
    }

    public boolean checkAvailability() {
        return available;
    }

    @Override
    public String toString() {
        return getItemType() + " [ID=" + itemId + ", Title=" + title +
                ", Author=" + author + ", Available=" + available + "]";
    }
}