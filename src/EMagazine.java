public class EMagazine extends LibraryItem {
    private final int issueNumber;

    public EMagazine(int itemId, String title, String author, int issueNumber) throws IllegalArgumentException {
        super(itemId, title, author);
        if (issueNumber <= 0) {
            throw new IllegalArgumentException("Issue number must be a positive integer");
        }
        this.issueNumber = issueNumber;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void archiveIssue() {
        System.out.println("Archiving issue " + issueNumber + " of " + getTitle());
    }

    @Override
    public String getItemType() {
        return "E-Magazine";
    }

    @Override
    public String toString() {
        return super.toString() + ", IssueNumber=" + issueNumber;
    }
}