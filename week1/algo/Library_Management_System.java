import java.util.Arrays;
import java.util.Comparator;

// Represents a book with an ID, title, and author
class Book {
    private int id;
    private String name;
    private String writer;

    // Constructor to initialize a Book object
    public Book(int id, String name, String writer) {
        this.id = id;
        this.name = name;
        this.writer = writer;
    }

    // Getter for book ID
    public int getId() {
        return id;
    }

    // Getter for book title
    public String getName() {
        return name;
    }

    // Getter for book author
    public String getWriter() {
        return writer;
    }

    @Override
    public String toString() {
        return "Book ID: " + id + ", Title: " + name + ", Author: " + writer;
    }
}

// Manages a collection of books and provides search functionalities
class LibraryManager {
    private Book[] catalog;
    private int count;

    // Constructor to initialize the LibraryManager with a specific capacity
    public LibraryManager(int capacity) {
        catalog = new Book[capacity];
        count = 0;
    }

    // Adds a new book to the catalog
    public void addBook(Book book) {
        if (count < catalog.length) {
            catalog[count++] = book;
        } else {
            System.out.println("The catalog is full. Unable to add more books.");
        }
    }

    // Finds a book by its title using linear search
    public Book searchByTitleLinear(String title) {
        for (int i = 0; i < count; i++) {
            if (catalog[i].getName().equalsIgnoreCase(title)) {
                return catalog[i];
            }
        }
        return null;
    }

    // Sorts the books by title for efficient binary search
    public void sortBooksByTitle() {
        Arrays.sort(catalog, 0, count, Comparator.comparing(Book::getName, String.CASE_INSENSITIVE_ORDER));
    }

    // Finds a book by its title using binary search
    public Book searchByTitleBinary(String title) {
        int left = 0;
        int right = count - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            int comparison = catalog[middle].getName().compareToIgnoreCase(title);
            if (comparison == 0) {
                return catalog[middle];
            } else if (comparison < 0) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return null;
    }
}

// Main class to demonstrate functionality
class Main {
    public static void main(String[] args) {
        // Initialize the LibraryManager with a capacity of 5 books
        LibraryManager manager = new LibraryManager(5);

        // Add books to the catalog
        manager.addBook(new Book(101, "To Kill a Mockingbird", "Harper Lee"));
        manager.addBook(new Book(102, "1984", "George Orwell"));
        manager.addBook(new Book(103, "The Great Gatsby", "F. Scott Fitzgerald"));
        manager.addBook(new Book(104, "Moby Dick", "Herman Melville"));
        manager.addBook(new Book(105, "The Catcher in the Rye", "J.D. Salinger"));

        // Search for a book by title using linear search
        System.out.println("Searching for '1984' using linear search:");
        Book book = manager.searchByTitleLinear("1984");
        if (book != null) {
            System.out.println("Found: " + book);
        } else {
            System.out.println("Book not found.");
        }

        // Sort books by title for binary search
        manager.sortBooksByTitle();

        // Search for a book by title using binary search
        System.out.println("\nSearching for 'The Great Gatsby' using binary search:");
        book = manager.searchByTitleBinary("The Great Gatsby");
        if (book != null) {
            System.out.println("Found: " + book);
        } else {
            System.out.println("Book not found.");
        }
    }
}
