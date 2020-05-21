package gameofbugs.view;

import gameofbugs.controller.SceneController;
import gameofbugs.model.Settings;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SettingsView {
    private HBox root;
    private SceneController sceneController;
    private Settings settings;

    public SettingsView(HBox root, SceneController sceneController) {
        this.root = root;
        this.sceneController = sceneController;
        this.settings = new Settings();
    }

    public void displaySettings() {

        boolean isCme = settings.isCME;
        boolean isQue = settings.isQUE;

        CheckBox QueBox = new CheckBox();
        QueBox.setSelected(isQue);
        CheckBox CmeBox = new CheckBox();
        CmeBox.setSelected(isCme);

        Button exitGameButton = new Button("Menu");
        exitGameButton.setOnMouseClicked(event -> {

            if(QueBox.isSelected()) settings.isQUE = true;
            else settings.isQUE = false;
            if(CmeBox.isSelected()) settings.isCME = true;
            else settings.isCME = false;

            sceneController.triggerMenu();
        }
        );


        root.getChildren().clear();
        root.getChildren().addAll(QueBox, new Label("  Play with QUE  "), CmeBox, new Label("  Play with CME  "), exitGameButton);
        root.setAlignment(Pos.CENTER);
    }
}
