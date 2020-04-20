package gameofbugs.view;

import gameofbugs.model.Color;
import gameofbugs.controller.SceneController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class EndGameView {
    private HBox root;
    private SceneController sceneController;
    private Color winner;

    public EndGameView(HBox root, Color winner, SceneController sceneController) {
        this.root = root;
        this.winner = winner;
        this.sceneController = sceneController;
    }

    public void displayGameEnd() {
        Label label = new Label(winner.toString() + " wins!");

        Button newGameButton = new Button("Play Again!");
        newGameButton.setOnMouseClicked(event -> sceneController.triggerGameStart());

        Button mainMenuButton = new Button("Back to menu");
        mainMenuButton.setOnMouseClicked(event -> sceneController.triggerMenu());

        Button exitGameButton = new Button("Exit");
        exitGameButton.setOnMouseClicked(event -> System.exit(0));

        root.getChildren().clear();
        root.getChildren().addAll(label, newGameButton, mainMenuButton, exitGameButton);
        root.setAlignment(Pos.CENTER);
    }
}
