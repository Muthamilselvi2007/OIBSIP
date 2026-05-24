 import javax.swing.JOptionPane;
import java.util.Random;
public class MuthamilselviS_Task2 {
    public static void main(String[] args) {
        int totalScore = 0, round = 1;
        boolean playAgain = true;
        while (playAgain) {
            int targetNumber = new Random().nextInt(100) + 1;
            int attempts = 0, maxAttempts = 5;
            boolean guessedCorrectly = false;
            while (attempts < maxAttempts && !guessedCorrectly) {
                String input = JOptionPane.showInputDialog(null, "Attempt " + (attempts + 1) + "/" + maxAttempts + "\nEnter your guess:", "Guessing...", JOptionPane.QUESTION_MESSAGE);
                if (input == null) return; // Exit if user clicks Cancel
                try {
                    int guess = Integer.parseInt(input);
                    attempts++;
                    if (guess == targetNumber) {
                        int points = (maxAttempts - attempts + 1) * 20; // 100 max points per round
                        totalScore += points;
                        JOptionPane.showMessageDialog(null, "🎉 Correct! You found it in " + attempts + " attempts.\nPoints earned: " + points + "\nTotal Score: " + totalScore, "Victory!", JOptionPane.INFORMATION_MESSAGE);
                        guessedCorrectly = true;
                    } else {
                        String direction = (guess < targetNumber) ? "Higher!" : "Lower!";
                        JOptionPane.showMessageDialog(null, "Wrong! The number is " + direction, "Hint", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid integer.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (!guessedCorrectly) {
                JOptionPane.showMessageDialog(null, "Out of attempts! The number was: " + targetNumber + "\nTotal Score: " + totalScore, "Game Over", JOptionPane.ERROR_MESSAGE);
            }
            int choice = JOptionPane.showConfirmDialog(null, "Play next round?", "Continue?", JOptionPane.YES_NO_OPTION);
            playAgain = (choice == JOptionPane.YES_OPTION);
            if (playAgain) round++;
        }
        JOptionPane.showMessageDialog(null, "Thanks for playing!\nFinal Score: " + totalScore, "Final Result", JOptionPane.INFORMATION_MESSAGE);
    }
}