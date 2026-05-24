import java.util.*;

class Book {
    String id, title, category, reservedBy = "";
    boolean isIssued = false;
    Book(String id, String title, String category) {
        this.id = id; this.title = title; this.category = category;
    }
}

public class MuthamilselviS_Task5 {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, Book> library = new HashMap<>();
    private static final List<String> members = new ArrayList<>();
    private static double totalFinesCollected = 0.0;

    static {
        library.put("101", new Book("101", "Java Programming", "Technical"));
        library.put("102", new Book("102", "Data Structures", "Technical"));
        members.add("Muthamil");
    }

    public static void main(String[] args) {
        System.out.println("=== AICTE OASIS DIGITAL LIBRARY MANAGEMENT ===");
        while (true) {
            System.out.print("\n1. Admin Module | 2. User Module | 3. Exit\nChoice: ");
            int choice = scanner.nextInt(); scanner.nextLine();
            if (choice == 1) showAdminMenu();
            else if (choice == 2) showUserMenu();
            else if (choice == 3) System.exit(0);
            else System.out.println("[ERROR] Invalid choice!");
        }
    }

    private static void showAdminMenu() {
        while (true) {
            System.out.print("\n[ADMIN] 1. Add Book | 2. Delete Book | 3. View System Report | 4. Back\nChoice: ");
            int choice = scanner.nextInt(); scanner.nextLine();
            if (choice == 1) {
                System.out.print("Book ID: "); String id = scanner.nextLine();
                System.out.print("Title: "); String title = scanner.nextLine();
                System.out.print("Category: "); String cat = scanner.nextLine();
                library.put(id, new Book(id, title, cat));
                System.out.println("[SUCCESS] Book added successfully!");
            } else if (choice == 2) {
                System.out.print("Enter Book ID to delete: "); String id = scanner.nextLine();
                if (library.remove(id) != null) System.out.println("[SUCCESS] Book deleted!");
                else System.out.println("[ERROR] Book not found!");
            } else if (choice == 3) {
                // Feature 3: Full Administrative Analytics Report
                System.out.println("\n=== SYSTEM ANALYTICS REPORT ===");
                long totalIssued = library.values().stream().filter(b -> b.isIssued).count();
                System.out.println("Total Books in Library: " + library.size());
                System.out.println("Total Books Currently Issued: " + totalIssued);
                System.out.println("Total Fine Revenue Collected: Rs. " + totalFinesCollected);
                System.out.println("===============================");
            } else if (choice == 4) return;
        }
    }

    private static void showUserMenu() {
        while (true) {
            System.out.print("\n[USER] 1. Search Books | 2. Issue Book | 3. Return & Fine Check | 4. Advance Booking | 5. Back\nChoice: ");
            int choice = scanner.nextInt(); scanner.nextLine();
            if (choice == 1) {
                System.out.print("Search by Category/Title (or leave blank for all): ");
                String query = scanner.nextLine().toLowerCase();
                for (Book b : library.values()) {
                    if (b.title.toLowerCase().contains(query) || b.category.toLowerCase().contains(query)) {
                        String status = b.isIssued ? "Issued" : "Available";
                        if (!b.reservedBy.isEmpty()) status += " (Reserved by " + b.reservedBy + ")";
                        System.out.printf("ID: %s | Title: %s | Category: %s | Status: %s\n", b.id, b.title, b.category, status);
                    }
                }
            } else if (choice == 2) {
                System.out.print("Enter Book ID to Issue: "); String id = scanner.nextLine();
                if (library.containsKey(id) && !library.get(id).isIssued) {
                    library.get(id).isIssued = true;
                    System.out.println("[SUCCESS] Book issued successfully!");
                } else System.out.println("[ERROR] Book is already issued or not found!");
            } else if (choice == 3) {
                // Feature 1: Automated Fine Calculation Logic
                System.out.print("Enter Book ID to Return: "); String id = scanner.nextLine();
                if (library.containsKey(id) && library.get(id).isIssued) {
                    System.out.print("Enter days delayed (0 if returned on time): ");
                    int days = scanner.nextInt(); scanner.nextLine();
                    if (days > 0) {
                        double fine = days * 5.0; // Rs. 5 per day fine
                        totalFinesCollected += fine;
                        System.out.println("[ALERT] Late Return! Fine Generated: Rs. " + fine);
                    }
                    library.get(id).isIssued = false;
                    library.get(id).reservedBy = ""; // Clear reservation upon return
                    System.out.println("[SUCCESS] Book returned successfully!");
                } else System.out.println("[ERROR] Invalid return process!");
            } else if (choice == 4) {
                // Feature 2: Advance Booking / Reservation Mechanism
                System.out.print("Enter Book ID to Reserve: "); String id = scanner.nextLine();
                if (library.containsKey(id) && library.get(id).isIssued) {
                    System.out.print("Enter Your Name for Reservation: ");
                    library.get(id).reservedBy = scanner.nextLine();
                    System.out.println("[SUCCESS] Advance booking registered for this book!");
                } else System.out.println("[ERROR] Book is already available in library or not found!");
            } else if (choice == 5) return;
        }
    }
}