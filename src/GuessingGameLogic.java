import java.util.Random;

public class GuessingGameLogic {
    // Target number is assigned once when the game starts and cannot change
    private final int TARGET_NUMBER;

    private static final int MAX_ATTEMPTS = 5;
    private int attempts;

    // Initialise a new game with a random target number and reset attempts
    public GuessingGameLogic() {
        Random random = new Random();
        TARGET_NUMBER = random.nextInt(50) + 1;
        attempts = 0;
    }

    // Validate the player's guess and return feedback based on the result
    public String checkGuess(int guess) {
        attempts++;

        if (guess < 1 || guess > 50) {
            return "Guess must be between 1 and 50.";
        }

        if (guess < TARGET_NUMBER) {
            return "Too low!";
        } else if (guess > TARGET_NUMBER) {
            return "Too high!";
        } else {
            return "Correct! The number is " + TARGET_NUMBER;
        }

    }

    // Determine whether the maximum number of attempts has been reached
    private boolean gameOver() {
        return attempts >= MAX_ATTEMPTS;
    }

    // Allow the view to display remaining attempts without hard-coding values
    public int getMaxAttempts() {
        return MAX_ATTEMPTS;
    }

    // Allow the view to display the current attempt count
    public int getAttempts() {
        return attempts;
    }

    // Expose the game-over state without allowing the view to modify game rules
    public boolean isGameOver() {
        return gameOver();
    }
}





