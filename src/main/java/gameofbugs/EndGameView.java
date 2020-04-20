package gameofbugs;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class EndGameView {
    private HBox root;
    private Color winner;
    private SceneController sceneController;

    public EndGameView(HBox root, Color winner, SceneController sceneController) {
        this.root = root;
        this.winner = winner;
        this.sceneController = sceneController;
    }

    public void displayGameEnd() {
        Label label = new Label(winner.toString() + " wins!");

        Button newGameButton = new Button("Play Again!");
        newGameButton.setOnMouseClicked(ActionEvent -> {
            sceneController.triggerGameStart();
        });

        Button mainMenuButton = new Button("Back to menu");
        mainMenuButton.setOnMouseClicked(ActionEvent -> {
            sceneController.triggerMenu();
        });

        Button exitGameButton = new Button("Exit");
        exitGameButton.setOnMouseClicked(ActionEvent -> {
            System.exit(0);
        });

        root.getChildren().clear();
        root.getChildren().addAll(label, newGameButton, mainMenuButton, exitGameButton);
        root.setAlignment(Pos.CENTER);
    }
}
