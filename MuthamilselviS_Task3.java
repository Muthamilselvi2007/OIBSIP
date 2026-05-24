import java.util.*;

// Class 1: Advanced Transaction Logger with Live Timestamp
class TransactionHistory {
    private final List<String> history = new ArrayList<>();
    public void addTransaction(String log) {
        String timestamp = new Date().toString();
        history.add("[" + timestamp + "] " + log);
    }
    public void showHistory() {
        if (history.isEmpty()) System.out.println("\nNo transactions found.");
        else {
            System.out.println("\n=== TRANSACTION HISTORY ===");
            history.forEach(System.out::println);
            System.out.println("===========================");
        }
    }
}

// Class 2: Core Banking Computations and Safety Limits
class Account {
    private double balance = 10000.0; 
    private double dailyWithdrawnAmount = 0.0;
    private static final double DAILY_LIMIT = 20000.0;

    public double getBalance() { return balance; }
    public void deposit(double amount) { balance += amount; }
    
    public int withdraw(double amount) {
        if (dailyWithdrawnAmount + amount > DAILY_LIMIT) return -1; // Limit exceeded
        if (amount > balance) return 0; // Insufficient balance
        balance -= amount;
        dailyWithdrawnAmount += amount;
        return 1; // Success
    }
}

// Class 3: Dynamic User Credentials Database
class BankDatabase {
    private final Map<String, String> credentials = new HashMap<>();
    public BankDatabase() { credentials.put("user123", "4321"); }
    public void registerUser(String id, String pin) { credentials.put(id, pin); }
    public void updatePin(String id, String newPin) { credentials.put(id, newPin); }
    public boolean authenticate(String id, String pin) {
        return credentials.containsKey(id) && credentials.get(id).equals(pin);
    }
}

// Class 4: ATM Operational Dashboard Screen
class ATMOperations {
    private final Account account = new Account();
    private final TransactionHistory txHistory = new TransactionHistory();
    private final BankDatabase db;
    private final String userId;
    private final Scanner scanner = new Scanner(System.in);

    public ATMOperations(BankDatabase db, String userId) {
        this.db = db;
        this.userId = userId;
    }

    public void startOperations() {
        while (true) {
            System.out.print("\n1. History | 2. Withdraw | 3. Deposit | 4. Transfer | 5. Change PIN | 6. Quit\nChoice: ");
            int choice = scanner.nextInt(); scanner.nextLine();
            if (choice == 1) txHistory.showHistory();
            else if (choice == 2) handleWithdraw();
            else if (choice == 3) handleDeposit();
            else if (choice == 4) handleTransfer();
            else if (choice == 5) handleChangePin();
            else if (choice == 6) { System.out.println("\n[SUCCESS] Session Closed!"); break; }
            else System.out.println("[ERROR] Invalid choice!");
        }
    }

    private void handleWithdraw() {
        System.out.println("\nCurrent Balance: Rs. " + account.getBalance());
        System.out.print("Enter amount to withdraw: "); double amt = scanner.nextDouble();
        int result = account.withdraw(amt);
        if (result == 1) {
            String log = "Withdrew: Rs. " + amt + " | Balance: Rs. " + account.getBalance();
            txHistory.addTransaction(log);
            System.out.println("[SUCCESS] Please collect cash. Final Balance: Rs. " + account.getBalance());
        } else if (result == -1) {
            System.out.println("[ERROR] Daily withdrawal limit of Rs. 20,000 exceeded!");
        } else {
            System.out.println("[ERROR] Insufficient balance!");
        }
    }

    private void handleDeposit() {
        System.out.println("\nCurrent Balance: Rs. " + account.getBalance());
        System.out.print("Enter amount to deposit: "); double amt = scanner.nextDouble();
        account.deposit(amt);
        String log = "Deposited: Rs. " + amt + " | Balance: Rs. " + account.getBalance();
        txHistory.addTransaction(log);
        System.out.println("[SUCCESS] Deposited successfully. Final Balance: Rs. " + account.getBalance());
    }

    private void handleTransfer() {
        System.out.println("\nCurrent Balance: Rs. " + account.getBalance());
        System.out.print("Enter recipient Account ID: "); String recipient = scanner.nextLine();
        System.out.print("Enter amount to transfer: "); double amt = scanner.nextDouble();
        if (account.withdraw(amt) == 1) {
            String log = "Transferred: Rs. " + amt + " to " + recipient + " | Balance: Rs. " + account.getBalance();
            txHistory.addTransaction(log);
            System.out.println("[SUCCESS] Transfer completed. Final Balance: Rs. " + account.getBalance());
        } else {
            System.out.println("[ERROR] Transfer failed due to limit restrictions or insufficient balance!");
        }
    }

    private void handleChangePin() {
        System.out.print("Enter New 4-Digit PIN: "); String newPin = scanner.nextLine();
        db.updatePin(userId, newPin);
        txHistory.addTransaction("Security PIN updated successfully.");
        System.out.println("[SUCCESS] PIN updated successfully!");
    }
}

// Class 5: Enterprise Engine Main Entry Point
public class MuthamilselviS_Task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankDatabase db = new BankDatabase();
        System.out.println("=== AICTE OASIS ATM INTERFACE ===");
        
        while (true) {
            System.out.print("\n1. Register | 2. Login | 3. Exit\nChoice: ");
            int initChoice = scanner.nextInt(); scanner.nextLine();
            
            if (initChoice == 1) {
                System.out.print("Create User ID: "); String regId = scanner.nextLine();
                System.out.print("Create 4-Digit PIN: "); String regPin = scanner.nextLine();
                db.registerUser(regId, regPin);
                System.out.println("[SUCCESS] Registration Successful!");
            } else if (initChoice == 2) {
                System.out.print("Enter User ID: "); String id = scanner.nextLine();
                System.out.print("Enter User PIN: "); String pin = scanner.nextLine();

                if (db.authenticate(id, pin)) {
                    System.out.println("[SUCCESS] Login Successful!");
                    ATMOperations atm = new ATMOperations(db, id);
                    atm.startOperations();
                } else {
                    System.out.println("[ERROR] Invalid Credentials!");
                }
            } else if (initChoice == 3) {
                System.out.println("Goodbye!");
                break;
            }
        }
        scanner.close();
    }
}