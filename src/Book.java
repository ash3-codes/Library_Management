public class Book extends LibraryItem {
    private final int pageCount;

    public Book(int itemId, String title, String author, int pageCount) throws IllegalArgumentException {
        super(itemId, title, author);
        if (pageCount <= 0) {
            throw new IllegalArgumentException("Page count must be a positive integer");
        }
        this.pageCount = pageCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    @Override
    public String getItemType() {
        return "Book";
    }

    @Override
    public String toString() {
        return super.toString() + ", PageCount=" + pageCount;
    }
}