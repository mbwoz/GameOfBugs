package gameofbugs.view;

import gameofbugs.controller.SceneController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class InstructionView {
    HBox root ;
    SceneController sceneController;

    public InstructionView(HBox root, SceneController sceneController) {
        this.root = root;
        this.sceneController = sceneController;
    }

    public void displayInstruction() {
        Label label = new Label("Work in progress");

        Button backToMenuButton = new Button("Back to menu");
        backToMenuButton.setOnMouseClicked(event -> sceneController.triggerMenu());

        root.getChildren().clear();
        root.getChildren().addAll(label, backToMenuButton);
        root.setAlignment(Pos.CENTER);
    }
}
