package gameofbugs;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.awt.event.ActionEvent;

public class MenuView {
    private HBox root;
    private Driver driver;

    public MenuView(HBox root, Driver driver) {
        this.root = root;
        this.driver = driver;
    }

    public void displayGameStart() {
        Button startGameButton = new Button("Start Game");
        startGameButton.setOnMouseClicked(ActionEvent -> {
            root.getChildren().clear();
            Platform.runLater(new Thread(){
                public void run(){
                    driver.launchGame();
                }
            });
        } );

        Button launchInstructionButton = new Button("Instruction");

        Button exitGameButton = new Button("Exit");
        exitGameButton.setOnMouseClicked(ActionEvent -> {
            System.exit(0);
        });

        root.getChildren().addAll(startGameButton, launchInstructionButton, exitGameButton);
        root.setAlignment(Pos.CENTER);

    }
}
