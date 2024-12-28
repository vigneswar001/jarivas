import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Company {
    private String name;
    private String registrationNumber;
    private String address;

    public Company(String name, String registrationNumber, String address) {
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Company Name: " + name +
               ", Registration Number: " + registrationNumber +
               ", Address: " + address;
    }
}

class CompanyManager {
    private List<Company> companies;

    public CompanyManager() {
        companies = new ArrayList<>();
    }

    public void addCompany(Company company) {
        companies.add(company);
        System.out.println("Company added: " + company.getName());
    }

    public void listCompanies() {
        System.out.println("List of Companies:");
        for (Company company : companies) {
            System.out.println(company);
        }
    }
}

public class CompanyManagementSystem {
    public static void main(String[] args) {
        CompanyManager companyManager = new CompanyManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Company Management System ---");
            System.out.println("1. Add Company");
            System.out.println("2. List Companies");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter company name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter registration number: ");
                    String registrationNumber = scanner.nextLine();
                    System.out.print("Enter address: ");
                    String address = scanner.nextLine();
                    companyManager.addCompany(new Company(name, registrationNumber, address));
                    break;
                case 2:
                    companyManager.listCompanies();
                    break;
                case 3:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
