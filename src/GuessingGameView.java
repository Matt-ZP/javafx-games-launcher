import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class GuessingGameView {

    private GuessingGameLogic game;
    private TextField guessField;

    private Label resultLabel;
    private Label attemptsLabel;
    private Label invalidInputLabel;

    private Button backButton;
    private Button submitButton;
    private Button playAgainButton;

    private static final int MAX_INVALID_INPUT = 5;
    private int invalidInput = 0;

    public VBox createView() {
        game = new GuessingGameLogic();
        Label titleLabel = new Label("Guessing Game");

        Label guessLabel = new Label("Guess a number between 1 and 50 in 5 attempts");
        guessField = new TextField();
        guessField.setMaxWidth(150);
        guessField.setOnAction(event -> processGuess());


        resultLabel = new Label();
        attemptsLabel = new Label("Attempts remaining: " + ( 5 - (game.getAttempts()) ) );
        invalidInputLabel = new Label("Invalid input: " + invalidInput);

        submitButton = new Button("Submit Guess");
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

    private void processGuess() {
        String inputText = guessField.getText().trim();
        String invalidInputReachedMessage = "GAME OVER - invalid input submissions reached. Play again?.";

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

        try {
            int guess = Integer.parseInt(inputText);

            String result = game.checkGuess(guess);

            resultLabel.setText(result);
            attemptsLabel.setText("Attempts remaining: " + ( 5 - (game.getAttempts()) ) );

            if (game.isGameOver()) {
                endGame("GAME OVER - Maximum attempts reached. Play again?");
            }

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

    private void endGame (String message) {
        resultLabel.setText(message);
        submitButton.setDisable(true);
        playAgainButton.setVisible(true);
    }

    private void resetGame() {
        game = new GuessingGameLogic();
        invalidInput = 0;

        resultLabel.setText("Result");
        attemptsLabel.setText("Attempts remaining: " +  (5 - (game.getAttempts()) ) );
        invalidInputLabel.setText("Invalid input: " + invalidInput);

        guessField.clear();
        submitButton.setDisable(false);
        playAgainButton.setVisible(false);
        guessField.requestFocus();
    }
}
