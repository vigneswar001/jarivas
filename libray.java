import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        // Sample data
        library.addBook(new Book("The Great Gatsby"));
        library.addBook(new Book("1984"));
        library.addBook(new Book("To Kill a Mockingbird"));

        System.out.println("Welcome to the Library Management System!");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Book");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. List Available Books");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    library.addBook(new Book(title));
                    break;
                case 2:
                    System.out.print("Enter patron name: ");
                    String patronName = scanner.nextLine();
                    Patron patron = new Patron(patronName);
                    System.out.print("Enter book title to borrow: ");
                    String borrowTitle = scanner.nextLine();
                    library.borrowBook(patron, borrowTitle);
                    break;
                case 3:
                    System.out.print("Enter patron name: ");
                    patronName = scanner.nextLine();
                    patron = new Patron(patronName);
                    System.out.print("Enter book title to return: ");
                    String returnTitle = scanner.nextLine();
                    library.returnBook(patron, returnTitle);
                    break;
                case 4:
                    library.listAvailableBooks();
                    break;
                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
