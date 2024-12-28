import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Library {
    private List<Book> books;
    private Map<String, Patron> patrons;
    private Map<Patron, List<Book>> borrowedBooks;

    public Library() {
        books = new ArrayList<>();
        patrons = new HashMap<>();
        borrowedBooks = new HashMap<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
    }

    public void addPatron(Patron patron) {
        patrons.put(patron.getPatronId(), patron);
        System.out.println("Patron added: " + patron.getName());
    }

    public void borrowBook(String patronId, String title) {
        Patron patron = patrons.get(patronId);
        if (patron == null) {
            System.out.println("Patron not found.");
            return;
        }

        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && book.isAvailable()) {
                book.setAvailable(false);
                borrowedBooks.computeIfAbsent(patron, k -> new ArrayList<>()).add(book);
                System.out.println(patron.getName() + " borrowed " + title);
                return;
            }
        }
        System.out.println("Book not available.");
    }

    public void returnBook(String patronId, String title) {
        Patron patron = patrons.get(patronId);
        if (patron == null) {
            System.out.println("Patron not found.");
            return;
        }

        List<Book> patronBooks = borrowedBooks.get(patron);
        if (patronBooks != null) {
            for (Book book : patronBooks) {
                if (book.getTitle().equalsIgnoreCase(title)) {
                    book.setAvailable(true);
                    patronBooks.remove(book);
                    System.out.println(patron.getName() + " returned " + title);
                    return;
                }
            }
        }
        System.out.println("This book was not borrowed by " + patron.getName());
    }

    public void listAvailableBooks() {
        System.out.println("Available books:");
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println("- " + book.getTitle() + " by " + book.getAuthor());
            }
        }
    }

    public void listPatrons() {
        System.out.println("Patrons:");
        for (Patron patron : patrons.values()) {
            System.out.println("- " + patron.getName() + " (ID: " + patron.getPatronId() + ")");
        }
    }
}
