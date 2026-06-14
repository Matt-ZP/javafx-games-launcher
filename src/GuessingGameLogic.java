import java.util.Random;

public class GuessingGameLogic {

    private final int TARGET_NUMBER;
    private int attempts;
    private static final int MAX_ATTEMPTS = 5;

    public GuessingGameLogic() {
        Random random = new Random();
        TARGET_NUMBER = random.nextInt(50) + 1;
        attempts = 0;
    }

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

    public int getAttempts() {
        return attempts;
    }

    private boolean gameOver() {
        return attempts >= MAX_ATTEMPTS;
        }

    public boolean isGameOver() {
        return gameOver();
    }

}





