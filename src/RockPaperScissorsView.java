import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class RockPaperScissorsView {

    private Button backButton;

    public VBox createView(){
        Label title = new Label("Hangman");
        backButton = new Button("Back to Menu");

        VBox layout = new VBox();
        layout.getChildren().addAll(title,backButton);

        return layout;
    }
    public Button getBackButton() {
        return backButton;
    }
}

