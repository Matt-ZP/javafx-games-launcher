import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameLauncher extends Application {

    // Standard window dimensions used throughout the application
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 700;

    // Create the application window, scenes and navigation
    @Override
    public void start(Stage stage) {
        stage.setTitle("MattZP Games");

        Button guessingGameButton = new Button("Guessing Game");
        Button hangmanButton = new Button("Hangman Game");
        Button rockPaperScissorsButton = new Button("Rock Paper Scissors");
        Button ticTacToeButton = new Button("Tic Tac Toe");
        Button exitGameButton = new Button("Exit");

        // Create view objects for each game
        GuessingGameView guessingGameView = new GuessingGameView();
        HangmanView hangmanView = new HangmanView();
        RockPaperScissorsView rockPaperScissorsView = new RockPaperScissorsView();
        TicTacToeView ticTacToeView = new TicTacToeView();

        // Create scenes for each game screen
        Scene guessingScene = new Scene(guessingGameView.createView(), WINDOW_WIDTH, WINDOW_HEIGHT);
        Scene hangmanScene = new Scene(hangmanView.createView(), WINDOW_WIDTH, WINDOW_HEIGHT);
        Scene rockPaperScissorsScene = new Scene(rockPaperScissorsView.createView(), WINDOW_WIDTH, WINDOW_HEIGHT);
        Scene ticTacToeScene = new Scene(ticTacToeView.createView(), WINDOW_WIDTH, WINDOW_HEIGHT);

        VBox menuLayout = createMenuLayout(
                guessingGameButton,
                hangmanButton,
                rockPaperScissorsButton,
                ticTacToeButton,
                exitGameButton
        );

        // Create the main menu scene
        Scene menuScene = new Scene(menuLayout, WINDOW_WIDTH, WINDOW_HEIGHT);

        // Configure navigation between menu and game scenes
        guessingGameButton.setOnAction(e -> stage.setScene(guessingScene));
        hangmanButton.setOnAction(e -> stage.setScene(hangmanScene));
        rockPaperScissorsButton.setOnAction(e -> stage.setScene(rockPaperScissorsScene));
        ticTacToeButton.setOnAction(e -> stage.setScene(ticTacToeScene));
        exitGameButton.setOnAction(e -> stage.close());

        // Return from game screens to the main menu
        guessingGameView.getBackButton().setOnAction(e -> {
            guessingGameView.resetGame();
            stage.setScene(menuScene);
        });

        hangmanView.getBackButton().setOnAction(e -> stage.setScene(menuScene));
        rockPaperScissorsView.getBackButton().setOnAction(e -> stage.setScene(menuScene));
        ticTacToeView.getBackButton().setOnAction(e -> stage.setScene(menuScene));

        stage.setScene(menuScene);
        stage.show();
    }

    private VBox createMenuLayout(
            Button guessingGameButton,
            Button hangmanButton,
            Button rockPaperScissorsButton,
            Button ticTacToeButton,
            Button exitGameButton
    ) {
        Label title = new Label("MattZP Games");

        VBox menuLayout = new VBox();
        menuLayout.setSpacing(10);

        menuLayout.getChildren().addAll(
                title,
                guessingGameButton,
                hangmanButton,
                rockPaperScissorsButton,
                ticTacToeButton,
                exitGameButton
        );

        return menuLayout;
    }
}