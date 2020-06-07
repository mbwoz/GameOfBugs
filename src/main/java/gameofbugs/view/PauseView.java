package gameofbugs.view;

import gameofbugs.controller.GameController;
import gameofbugs.controller.SceneController;
import gameofbugs.model.GameModel;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PauseView {
    private GameView gameView;
    private GameModel gameModel;
    private HBox root;
    private SceneController sceneController;

    public PauseView(HBox root, SceneController sceneController, GameView gameView,
                     GameModel gameModel) {
        this.root = root;
        this.sceneController = sceneController;
        this.gameView = gameView;
        this.gameModel = gameModel;
    }

    public void displayPauseMenu() {
        Image yesButton = null;
        Image noButton = null;
        Image background = null;

        ImageView yesButtonView = null;
        ImageView noButtonView = null;
        ImageView backgroundView = null;

        yesButton = new Image(getClass().getResourceAsStream("/PauseMenuYes.png"));
        noButton = new Image(getClass().getResourceAsStream("/PauseMenuNo.png"));
        background = new Image(getClass().getResourceAsStream("/PauseMenuBackground.png"));

        backgroundView = new ImageView(background);

        yesButtonView = new ImageView(yesButton);
        yesButtonView.setOnMouseClicked(event -> sceneController.triggerMenu());
        yesButtonView.setCursor(Cursor.HAND);

        noButtonView = new ImageView(noButton);
        noButtonView.setOnMouseClicked(event -> sceneController.triggerResumeGame(gameModel, gameView));
        noButtonView.setCursor(Cursor.HAND);


        Group group = new Group(backgroundView, yesButtonView, noButtonView);

        root.getChildren().clear();
        root.getChildren().add(group);
    }
}
