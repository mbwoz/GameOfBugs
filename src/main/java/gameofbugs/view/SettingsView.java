package gameofbugs.view;

import gameofbugs.controller.SceneController;
import gameofbugs.model.Settings;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicBoolean;

public class SettingsView {
    private HBox root;
    private SceneController sceneController;

    public SettingsView(HBox root, SceneController sceneController) {
        this.root = root;
        this.sceneController = sceneController;
    }

    public void displaySettings() {
        Group group = new Group();

        AtomicBoolean isCme = new AtomicBoolean(Settings.isCME);
        AtomicBoolean isQue = new AtomicBoolean(Settings.isQUE);

        Image background = null;
        Image backButton = null;
        Image que0Button = null;
        Image que1Button = null;
        Image cme0Button = null;
        Image cme1Button = null;

        try {
            background = new Image(new FileInputStream("src/main/resources/SettingsBackground.png"));
            backButton = new Image(new FileInputStream("src/main/resources/SettingsBack.png"));
            que0Button = new Image(new FileInputStream("src/main/resources/SettingsQue0.png"));
            que1Button = new Image(new FileInputStream("src/main/resources/SettingsQue1.png"));
            cme0Button = new Image(new FileInputStream("src/main/resources/SettingsCme0.png"));
            cme1Button = new Image(new FileInputStream("src/main/resources/SettingsCme1.png"));
        } catch(FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }

        ImageView backgroundView = new ImageView(background);
        ImageView backButtonView = new ImageView(backButton);
        ImageView que0ButtonView = new ImageView(que0Button);
        ImageView que1ButtonView = new ImageView(que1Button);
        ImageView cme0ButtonView = new ImageView(cme0Button);
        ImageView cme1ButtonView = new ImageView(cme1Button);


        backButtonView.setOnMouseClicked(event -> {

            Settings.isQUE = isQue.get();
            Settings.isCME = isCme.get();

            sceneController.triggerMenu();
        }
        );
        backButtonView.setCursor(Cursor.HAND);

        que0ButtonView.setOnMouseClicked(event -> {

            isQue.set(true);
            group.getChildren().remove(que0ButtonView);
            group.getChildren().add(que1ButtonView);
            root.getChildren().clear();
            root.getChildren().add(group);
        }
        );
        que0ButtonView.setCursor(Cursor.HAND);

        que1ButtonView.setOnMouseClicked(event -> {

                    isQue.set(false);
                    group.getChildren().remove(que1ButtonView);
                    group.getChildren().add(que0ButtonView);
                    root.getChildren().clear();
                    root.getChildren().add(group);
                }
        );
        que1ButtonView.setCursor(Cursor.HAND);

        cme0ButtonView.setOnMouseClicked(event -> {

                    isCme.set(true);
                    group.getChildren().remove(cme0ButtonView);
                    group.getChildren().add(cme1ButtonView);
                    root.getChildren().clear();
                    root.getChildren().add(group);
                }
        );
        cme0ButtonView.setCursor(Cursor.HAND);

        cme1ButtonView.setOnMouseClicked(event -> {

                    isCme.set(false);
                    group.getChildren().remove(cme1ButtonView);
                    group.getChildren().add(cme0ButtonView);
                    root.getChildren().clear();
                    root.getChildren().add(group);
                }
        );
        cme1ButtonView.setCursor(Cursor.HAND);


        group.getChildren().addAll(backgroundView, backButtonView);

        if(isCme.get())
            group.getChildren().add(cme1ButtonView);
        else
            group.getChildren().add(cme0ButtonView);

        if(isQue.get())
            group.getChildren().add(que1ButtonView);
        else
            group.getChildren().add(que0ButtonView);

        root.getChildren().clear();
        root.getChildren().add(group);
    }
}
