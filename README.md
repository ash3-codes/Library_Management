# 📚 LibraNet: Library Management System

<p align="left">
    <img src="https://img.shields.io/badge/Java-8+-blue.svg" alt="Java">
    <img src="https://img.shields.io/badge/Maven-3.6+-brightgreen.svg" alt="Maven">
</p>

LibraNet is a robust, object-oriented library management system built with Java. It provides a comprehensive platform for managing books, audiobooks, and e-magazines, complete with a full-featured borrowing system, automated fine calculation, and powerful search capabilities.

---



---

## ✨ Key Features

| Feature | Description |
| --- | --- |
| **Item Management** | Add, track, and manage a diverse collection of books, audiobooks, and e-magazines. |
| **Borrowing System** | Seamlessly borrow and return items with automated due date tracking. |
| **Fine Calculation** | Automated, strategy-based fine calculation for overdue items. |
| **Powerful Search** | Quickly find items by type, title, or author. |
| **Extensible Design** | Built on clean OOP principles, making it easy to add new item types or fine strategies. |
| **Robust Error Handling** | Includes comprehensive validation and custom exceptions for reliable operation. |

---

## 🏗️ Project Structure

```bash
LibraNet/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── LibraryItem.java        # Abstract base class for all library items
│   │   │   ├── Playable.java           # Interface for playable items (audiobooks)
│   │   │   ├── Book.java               # Book implementation
│   │   │   ├── Audiobook.java          # Audiobook implementation (implements Playable)
│   │   │   ├── EMagazine.java          # E-Magazine implementation
│   │   │   ├── FineCalculator.java     # Interface for fine calculation strategies
│   │   │   ├── DefaultFineCalculator.java # Default fine calculation implementation
│   │   │   ├── BorrowingRecord.java    # Tracks borrowing information
│   │   │   ├── Library.java            # Main library management class
│   │   │   └── LibraryDemo.java        # Example usage demonstration
├── README.md
├── .gitignore
└── pom.xml                 # Maven build configuration
