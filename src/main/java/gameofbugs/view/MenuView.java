package gameofbugs.view;

import gameofbugs.controller.SceneController;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MenuView {
    private HBox root;
    private SceneController sceneController;

    public MenuView(HBox root, SceneController sceneController) {
        this.root = root;
        this.sceneController = sceneController;
    }

    public void displayGameStart() {
        Image background = new Image(getClass().getResourceAsStream("/MenuBackground.png"));
        Image startButton = new Image(getClass().getResourceAsStream("/MenuStart.png"));
        Image tutorialButton = new Image(getClass().getResourceAsStream("/MenuTutorial.png"));
        Image optionsButton = new Image(getClass().getResourceAsStream("/MenuOptions.png"));
        Image exitButton = new Image(getClass().getResourceAsStream("/MenuExit.png"));

        ImageView backgroundView = null;
        ImageView startButtonView = null;
        ImageView tutorialButtonView = null;
        ImageView optionsButtonView = null;
        ImageView exitButtonView = null;

        backgroundView = new ImageView(background);

        startButtonView = new ImageView(startButton);
        startButtonView.setOnMouseClicked(event -> sceneController.triggerGameStart());
        startButtonView.setCursor(Cursor.HAND);

        tutorialButtonView = new ImageView(tutorialButton);
        tutorialButtonView.setOnMouseClicked(event -> sceneController.triggerInstruction());
        tutorialButtonView.setCursor(Cursor.HAND);

        optionsButtonView = new ImageView(optionsButton);
        optionsButtonView.setOnMouseClicked(event -> sceneController.triggerSettings());
        optionsButtonView.setCursor(Cursor.HAND);

        exitButtonView = new ImageView(exitButton);
        exitButtonView.setOnMouseClicked(event -> System.exit(0));
        exitButtonView.setCursor(Cursor.HAND);


        Group group = new Group(backgroundView, startButtonView, tutorialButtonView, optionsButtonView, exitButtonView);

        root.getChildren().clear();
        root.getChildren().add(group);
    }
}
