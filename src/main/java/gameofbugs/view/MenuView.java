package gameofbugs.view;

import gameofbugs.controller.SceneController;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Button;
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
        Image background = null;
        Image startButton = null;
        Image tutorialButton = null;
        Image optionsButton = null;
        Image exitButton = null;

        ImageView backgroundView = null;
        ImageView startButtonView = null;
        ImageView tutorialButtonView = null;
        ImageView optionsButtonView = null;
        ImageView exitButtonView = null;

        try {
            background = new Image(new FileInputStream("src/main/resources/MenuBackground.png"));
            startButton = new Image(new FileInputStream("src/main/resources/MenuStart.png"));
            tutorialButton = new Image(new FileInputStream("src/main/resources/MenuTutorial.png"));
            optionsButton = new Image(new FileInputStream("src/main/resources/MenuOptions.png"));
            exitButton = new Image(new FileInputStream("src/main/resources/MenuExit.png"));
        } catch(FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }

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


        Button startGameButton = new Button("Start Game");
        startGameButton.setOnMouseClicked(event -> sceneController.triggerGameStart());

        Button launchInstructionButton = new Button("Instruction");
        launchInstructionButton.setOnMouseClicked(event -> sceneController.triggerInstruction());

        Button launchSettingsButton = new Button("Settings");
        launchSettingsButton.setOnMouseClicked(event -> sceneController.triggerSettings());

        Button exitGameButton = new Button("Exit");
        exitGameButton.setOnMouseClicked(event -> System.exit(0));


        Group group = new Group(backgroundView, startButtonView, tutorialButtonView, optionsButtonView, exitButtonView);

        root.getChildren().clear();
        root.getChildren().add(group);
    }
}
