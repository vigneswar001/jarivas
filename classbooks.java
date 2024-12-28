import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Library {
    private List<Book> books;
    private Map<Patron, List<Book>> borrowedBooks;

    public Library() {
        books = new ArrayList<>();
        borrowedBooks = new HashMap<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
    }

    public void borrowBook(Patron patron, String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && !book.isBorrowed()) {
                book.setBorrowed(true);
                borrowedBooks.computeIfAbsent(patron, k -> new ArrayList<>()).add(book);
                System.out.println(patron.getName() + " borrowed " + title);
                return;
            }
        }
        System.out.println("Book not available.");
    }

    public void returnBook(Patron patron, String title) {
        List<Book> patronBooks = borrowedBooks.get(patron);
        if (patronBooks != null) {
            for (Book book : patronBooks) {
                if (book.getTitle().equalsIgnoreCase(title)) {
                    book.setBorrowed(false);
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
            if (!book.isBorrowed()) {
                System.out.println("- " + book.getTitle());
            }
        }
    }
}
