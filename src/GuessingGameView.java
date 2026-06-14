import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class GuessingGameView {

    // Game logic object containing rules, target number and attempt tracking
    private GuessingGameLogic game;

    // User input field for guesses
    private TextField guessField;

    // Labels used to display game status and feedback
    private Label resultLabel;
    private Label attemptsLabel;
    private Label invalidInputLabel;

    // Navigation and game control buttons
    private Button backButton;
    private Button submitButton;
    private Button playAgainButton;

    // Maximum invalid submissions allowed before ending the game
    private static final int MAX_INVALID_INPUT = 5;
    private int invalidInput = 0;

    // Build and return the Guessing Game user interface
    public VBox createView() {
        game = new GuessingGameLogic();
        Label titleLabel = new Label("Guessing Game");

        Label guessLabel = new Label("Guess a number between 1 and 50 in 5 attempts");
        guessField = new TextField();
        guessField.setMaxWidth(150);
        // Pressing Enter in the text field processes the guess
        guessField.setOnAction(event -> processGuess());


        resultLabel = new Label();
        attemptsLabel = new Label("Attempts remaining: " + ((game.getMaxAttempts()) - (game.getAttempts())));
        invalidInputLabel = new Label("Invalid input: " + invalidInput);

        submitButton = new Button("Submit Guess");
        // Clicking the Submit button processes the guess
        submitButton.setOnAction(event -> processGuess());

        playAgainButton = new Button("Play Again");
        playAgainButton.setVisible(false);
        playAgainButton.setOnAction(event -> resetGame());

        backButton = new Button("Back to Menu");

        VBox layout = new VBox();
        layout.setSpacing(10);

        layout.getChildren().addAll(
                titleLabel,
                guessLabel,
                guessField,
                submitButton,
                resultLabel,
                attemptsLabel,
                invalidInputLabel,
                playAgainButton,
                backButton);

        return layout;
    }

    public Button getBackButton() {
        return backButton;
    }

    // Process a user's guess, update the UI and determine whether the game should end
    private void processGuess() {
        String inputText = guessField.getText().trim();
        String invalidInputReachedMessage = "GAME OVER - invalid input submissions reached. Play again?";

        // Prevent blank submissions and track invalid input attempts
        if (inputText.isEmpty()) {
            invalidInput++;
            invalidInputLabel.setText("Invalid input: " + invalidInput);

            if (invalidInput >= MAX_INVALID_INPUT) {
                endGame(invalidInputReachedMessage);
                return;
            }

            resultLabel.setText("Submission cannot be blank. Please enter a whole number.");
            guessField.requestFocus();
            return;
        }

        // Convert user input to an integer and pass it to the game logic
        try {
            int guess = Integer.parseInt(inputText);

            String result = game.checkGuess(guess);

            resultLabel.setText(result);
            attemptsLabel.setText("Attempts remaining: " + ((game.getMaxAttempts()) - (game.getAttempts())));

            if (game.isGameOver()) {
                endGame("GAME OVER - Maximum attempts reached. Play again?");
            }

        // Handle non-numeric input without crashing the application
        } catch (NumberFormatException e) {
            invalidInput++;
            invalidInputLabel.setText("Invalid input: " + invalidInput);

            if (invalidInput >= MAX_INVALID_INPUT) {
                endGame(invalidInputReachedMessage);
                return;

            }
            resultLabel.setText("Only whole numbers are allowed.");
        }

        guessField.clear();
        guessField.requestFocus();
    }

    // Disable further guesses and display replay option
    private void endGame(String message) {
        resultLabel.setText(message);
        submitButton.setDisable(true);
        playAgainButton.setVisible(true);
    }

    // Reset game state and UI components ready for a new game
    public void resetGame() {
        game = new GuessingGameLogic();
        invalidInput = 0;

        resultLabel.setText("Result");
        attemptsLabel.setText("Attempts remaining: " + ((game.getMaxAttempts()) - (game.getAttempts())));
        invalidInputLabel.setText("Invalid input: " + invalidInput);

        guessField.clear();
        submitButton.setDisable(false);
        playAgainButton.setVisible(false);
        guessField.requestFocus();
    }
}
