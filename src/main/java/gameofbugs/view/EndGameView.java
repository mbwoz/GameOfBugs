package gameofbugs.view;

import gameofbugs.model.Color;
import gameofbugs.controller.SceneController;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
        Image background = new Image(getClass().getResourceAsStream("/Endgame" + winner.toString() + ".png"));
        Image playButton = new Image(getClass().getResourceAsStream("/EndgamePlay.png"));
        Image menuButton = new Image(getClass().getResourceAsStream("/EndgameMenu.png"));

        ImageView backgroundView = new ImageView(background);
        ImageView playButtonView = new ImageView(playButton);
        ImageView menuButtonView = new ImageView(menuButton);

        playButtonView.setOnMouseClicked(event -> sceneController.triggerGameStart());
        playButtonView.setCursor(Cursor.HAND);

        menuButtonView.setOnMouseClicked(event -> sceneController.triggerMenu());
        menuButtonView.setCursor(Cursor.HAND);

        Group group = new Group(backgroundView, playButtonView, menuButtonView);

        root.getChildren().clear();
        root.getChildren().add(group);
    }
}
