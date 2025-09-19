import java.util.List;

public class LibraryDemo {
    public static void main(String[] args) {
        try {
            // Create
            Library library = new Library();

            // Add items
            library.addItem(new Book(1, "One Piece", "Eichiiro Oda", 1143));
            library.addItem(new Audiobook(2, "Jujustu Kaisen", "Gege Akutami", 360.5));
            library.addItem(new EMagazine(3, "Demon Slayer", "Koyoharu Gotouge", 202));

            // Borrow
            BorrowingRecord record = library.borrowItem(1, "A P Dhillon", "14 days");
            System.out.println("Borrowed: " + record);

            // Search
            List<LibraryItem> books = library.searchByType("Book");
            System.out.println("Books in library: " + books);

            // Return
            BorrowingRecord returnRecord = library.returnItem(1);
            System.out.println("Returned: " + returnRecord);

            // audiobook functionality
            Audiobook audiobook = (Audiobook) library.getItem(2);
            audiobook.play();
            System.out.println("Duration: " + audiobook.getDuration() + " minutes");

            // e-magazine functionality
            EMagazine magazine = (EMagazine) library.getItem(3);
            magazine.archiveIssue();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}