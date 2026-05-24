import java.util.*;

public class MuthamilselviS_Task4 {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, String> database = new HashMap<>();
    private static String currentUser = null;
    private static Thread timerThread;
    private static volatile int timeRemaining = 120; 
    private static volatile boolean isTimeUp = false, isExamFinished = false;

    public static void main(String[] args) {
        System.out.println("=== AICTE OASIS ONLINE EXAMINATION SYSTEM ===");
        while (currentUser == null) {
            System.out.print("\n1. Register | 2. Login | 3. Exit\nChoice: ");
            int choice = scanner.nextInt(); scanner.nextLine(); 
            if (choice == 1) {
                System.out.print("Username: "); String u = scanner.nextLine();
                System.out.print("Password: "); String p = scanner.nextLine();
                database.put(u, p); System.out.println("[SUCCESS] Registered!");
            } else if (choice == 2) {
                System.out.print("Username: "); String u = scanner.nextLine();
                System.out.print("Password: "); String p = scanner.nextLine();
                if (database.containsKey(u) && database.get(u).equals(p)) {
                    currentUser = u; System.out.println("[SUCCESS] Logged In!"); showMenu();
                } else System.out.println("[ERROR] Invalid credentials!");
            } else if (choice == 3) System.exit(0);
        }
    }

    private static void showMenu() {
        while (currentUser != null) {
            System.out.print("\n1. Update Password | 2. Start Exam | 3. Logout\nChoice: ");
            switch (scanner.nextInt()) {
                case 1 -> { System.out.print("New Password: "); scanner.nextLine(); database.put(currentUser, scanner.nextLine()); System.out.println("[SUCCESS] Updated!"); }
                case 2 -> startExam();
                case 3 -> { currentUser = null; System.out.println("[SUCCESS] Logged out!"); }
                default -> System.out.println("[ERROR] Invalid choice!");
            }
        }
    }

    private static void startExam() {
        scanner.nextLine(); isTimeUp = false; isExamFinished = false; timeRemaining = 120; 
        System.out.println("\n[INFO] Exam started with Live Timer...");
        timerThread = new Thread(() -> {
            while (timeRemaining > 0 && !isExamFinished) {
                try {
                    System.out.print("\r[LIVE TIMER] Time Remaining: " + timeRemaining + " seconds ");
                    Thread.sleep(1000); timeRemaining--;
                } catch (InterruptedException e) { break; }
            }
            if (timeRemaining <= 0 && !isExamFinished) { isTimeUp = true; System.out.println("\n\n[ALERT] Time's up! Press ENTER."); }
        });
        timerThread.start();
        
        String[][] mcqs = {
            {"Who invented Java?", "A) James Gosling", "B) Dennis Ritchie", "A"},
            {"Which component compiles Java?", "A) JRE", "B) JDK", "B"},
            {"Which of these is NOT a primitive data type?", "A) int", "B) String", "B"},
            {"What is the extension of a compiled class?", "A) .java", "B) .class", "B"},
            {"Which memory stores objects in Java?", "A) Stack", "B) Heap", "B"}
        };
        int score = 0;

        for (int i = 0; i < mcqs.length; i++) {
            if (isTimeUp) break;
            System.out.printf("\n\nQuestion %d: %s\n%s\n%s\nAnswer (A/B): ", (i + 1), mcqs[i][0], mcqs[i][1], mcqs[i][2]);
            String answer = scanner.nextLine().toUpperCase().trim();
            if (isTimeUp) break;
            if (answer.equals(mcqs[i][3])) score++;
        }
        isExamFinished = true; timerThread.interrupt(); 
        System.out.printf("\n=================================\n[RESULT] Finished! Score: %d/%d\n=================================\n", score, mcqs.length);
    }
}