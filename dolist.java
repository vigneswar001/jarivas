import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {
    public static void main(String[] args) {
        ArrayList<String> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Welcome to the To-Do List Application!");
        
        while (true) {
            System.out.println("\nEnter a command: (add/view/remove/exit)");
            command = scanner.nextLine().toLowerCase();

            switch (command) {
                case "add":
                    System.out.print("Enter a task to add: ");
                    String task = scanner.nextLine();
                    tasks.add(task);
                    System.out.println("Task added: " + task);
                    break;
                case "view":
                    System.out.println("Your tasks:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                    break;
                case "remove":
                    System.out.print("Enter task number to remove: ");
                    int taskNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    if (taskNumber > 0 && taskNumber <= tasks.size()) {
                        String removedTask = tasks.remove(taskNumber - 1);
                        System.out.println("Removed task: " + removedTask);
                    } else {
                        System.out.println("Invalid task number.");
                    }
                    break;
                case "exit":
                    System.out.println("Exiting the application. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }
    }
}
