import java.util.*;
public class MuthamilselviS_Task1 {
    public static void main(String[] args) {
        Map<Integer, List<String>> res = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        int pnr = 10001;
        System.out.println("=== Welcome to Online Reservation System ===");
        // 1. SIGN UP PAGE (Extra Feature)
        System.out.println("\n--- NEW USER SIGN UP ---");
        System.out.print("Create Login ID (Username): ");
        String regUser = sc.nextLine();
        System.out.print("Create Password: ");
        String regPass = sc.nextLine();
        System.out.println("Registration Successful! Now you can login.");
        // 2. LOGIN PAGE
        System.out.println("\n--- LOGIN PAGE ---");
        System.out.print("Enter Login ID (Username): ");
        String loginUser = sc.nextLine();
        System.out.print("Enter Password: ");
        String loginPass = sc.nextLine();
        // Validating the credentials created during sign-up
        if (loginUser.equals(regUser) && loginPass.equals(regPass)) {
            System.out.println("Login Successful! Welcome, " + loginUser + ".");
            while (true) {
                System.out.print("\n--- MAIN MENU ---\n1. Ticket Reservation (Reservation Form)\n2. Ticket Cancellation (Cancellation Form)\n3. Exit\nEnter your choice (1-3): ");
                int choice = sc.nextInt(); sc.nextLine();
                if (choice == 1) {
                    System.out.println("\n--- TICKET RESERVATION FORM ---");
                    List<String> d = new ArrayList<>();
                    String[] prompts = {"Passenger Name", "Train Number", "Class Type (e.g., Sleeper, AC)", "Date of Journey (DD-MM-YYYY)", "From (Place)", "Destination (To)"};
                    for (String p : prompts) { System.out.print("Enter " + p + ": "); d.add(sc.nextLine()); }    
                    String tn = d.get(1).equals("12601") ? "Chennai Mail" : d.get(1).equals("12673") ? "Cheran Express" : "Express Train";
                    d.add(2, tn); System.out.println("Train Name (Auto-filled): " + tn); 
                    System.out.print("\nType 'INSERT' to save your reservation details: ");
                    if (sc.nextLine().equalsIgnoreCase("INSERT")) {
                        res.put(pnr, d);
                        System.out.println("\nReservation Successful!\nYour generated PNR Number is: " + pnr++);
                    } else System.out.println("Reservation Cancelled (Data not inserted).");
                } 
                else if (choice == 2) {
                    System.out.print("\n--- TICKET CANCELLATION FORM ---\nEnter your PNR Number: ");
                    int inputPnr = sc.nextInt(); sc.nextLine();
                    if (res.containsKey(inputPnr)) {
                        List<String> d = res.get(inputPnr);
                        System.out.printf("\n--- Current Ticket Details ---\nPNR Number: %d\nPassenger Name: %s\nTrain: %s - %s\nClass: %s | Date: %s\nJourney: From %s to %s\n", inputPnr, d.get(0), d.get(1), d.get(2), d.get(3), d.get(4), d.get(5), d.get(6));
                        System.out.print("\nType 'OK' to confirm ticket cancellation: ");
                        if (sc.nextLine().equalsIgnoreCase("OK")) { res.remove(inputPnr); System.out.println("Your ticket (PNR: " + inputPnr + ") has been successfully cancelled."); }
                        else System.out.println("Cancellation aborted.");
                    } else System.out.println("Invalid PNR Number! No record found.");
                } 
                else if (choice == 3) { System.out.println("Thank you for using our system!"); break; }
                else System.out.println("Invalid choice! Please try again.");
            }
        } else System.out.println("Access Denied! Invalid Username or Password.");
    }
}