#LibraNet Library Management System
A robust Java-based library management platform that handles books, audiobooks, and e-magazines with comprehensive borrowing, fine calculation, and search capabilities.

📚 Project Overview
LibraNet is designed to manage a diverse collection of library items with shared attributes and specialized behaviors. The system implements clean object-oriented principles to ensure reusability, extensibility, and robust data handling.

🏗️ Project Structure

LibraNet/
│
├── src/
│   └── main/
│       └── java/
│           ├── LibraryItem.java         # Abstract base class for all library items
│           ├── Playable.java            # Interface for playable items (audiobooks)
│           ├── Book.java                # Book implementation
│           ├── Audiobook.java           # Audiobook implementation (implements Playable)
│           ├── EMagazine.java           # E-Magazine implementation
│           ├── FineCalculator.java      # Interface for fine calculation strategies
│           ├── DefaultFineCalculator.java # Default fine calculation implementation
│           ├── BorrowingRecord.java     # Tracks borrowing information
│           ├── Library.java             # Main library management class
│           └── LibraryDemo.java         # Example usage demonstration
│
├── README.md
├── .gitignore
└── pom.xml                            # Maven build configuration
✨ Key Features
Core Functionality
Item Management: Add, track, and manage books, audiobooks, and e-magazines
Borrowing System: Borrow and return items with due date tracking
Fine Calculation: Automated fine calculation based on overdue days
Search Capabilities: Search by item type, title, or author
Specialized Behaviors:
Books: Page count tracking
Audiobooks: Playback controls and duration tracking
E-Magazines: Issue archiving functionality
Design Principles
Clean Architecture: Separation of concerns with clear class responsibilities
Extensibility: Easy to add new item types or fine calculation strategies
Robust Error Handling: Comprehensive validation and custom exceptions
Type Safety: Strong typing throughout the system
Flexible Data Handling: Proper parsing of borrowing durations and ID management
🚀 Getting Started
Prerequisites
Java 8 or higher
Maven 3.6 or higher (for build management)
Building the Project


# Clone the repository
git clone https://github.com/yourusername/LibraNet.git

# Navigate to the project directory
cd LibraNet

# Compile the project
mvn compile

# Run the demo
mvn exec:java -Dexec.mainClass="LibraryDemo"
Basic Usage Example
java


// Create a library
Library library = new Library();

// Add items to the library
library.addItem(new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", 180));
library.addItem(new Audiobook(2, "To Kill a Mockingbird", "Harper Lee", 360.5));
library.addItem(new EMagazine(3, "National Geographic", "Various Authors", 202));

// Borrow an item
BorrowingRecord record = library.borrowItem(1, "John Doe", "14 days");

// Search for items
List<LibraryItem> books = library.searchByType("Book");

// Return an item
BorrowingRecord returnRecord = library.returnItem(1);
📖 Class Documentation
Core Classes
LibraryItem
Abstract base class for all library items with common attributes and behaviors:

Attributes: itemId, title, author, availability status
Methods: borrow(), returnItem(), checkAvailability()
Book
Extends LibraryItem with specialized behavior:

Additional attribute: pageCount
Method: getPageCount()
Audiobook
Extends LibraryItem and implements Playable interface:

Additional attribute: duration (in minutes)
Playable methods: play(), pause(), stop()
EMagazine
Extends LibraryItem with specialized behavior:

Additional attribute: issueNumber
Method: archiveIssue()
Supporting Classes
Playable
Interface for items with playback capabilities:

Methods: play(), pause(), stop(), getDuration()
FineCalculator
Interface for fine calculation strategies:

Method: calculateFine(dueDate, returnDate)
DefaultFineCalculator
Default implementation of FineCalculator:

Calculates fines based on a per-day rate (default: 10 rs/day)
BorrowingRecord
Tracks borrowing information:

Attributes: item, borrower, dates, fine calculator
Methods: calculateFine(), returnItem()
Library
Main management class:

Methods: addItem(), borrowItem(), returnItem(), searchByType(), etc.
Custom exceptions for error handling
🔧 Customization
Adding New Item Types
Create a new class extending LibraryItem
Implement required methods and add specialized attributes
Register the new type in the Library class
Implementing Custom Fine Calculators
Create a class implementing FineCalculator
Implement the calculateFine() method
Pass the custom calculator to the Library constructor
Extending Search Capabilities
Add new search methods to the Library class following the pattern of existing search methods.

🧪 Testing
The project includes a LibraryDemo class that demonstrates core functionality. To run the demo:



mvn exec:java -Dexec.mainClass="LibraryDemo"
For comprehensive testing, we recommend adding JUnit test cases in the src/test/java directory.


🙏 Acknowledgments
Designed with object-oriented principles in mind
Inspired by real-world library management systems
Built for extensibility and maintainability
📞 Contact
For questions or suggestions, please open an issue in the repository or contact ashutoshsahu0903@gmail.com.
