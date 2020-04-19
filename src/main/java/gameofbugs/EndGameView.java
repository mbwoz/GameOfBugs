package gameofbugs;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.awt.event.ActionEvent;

public class EndGameView {
    private HBox root;
    private Color winner;
    private Driver driver;

    public EndGameView(HBox root, Color winner, Driver driver) {
        this.root = root;
        this.winner = winner;
        this.driver = driver;
    }

    public void displayGameEnd() {
        Label label = new Label(winner.toString() + " wins!");

        Button newGameButton = new Button("Play Again!");
        newGameButton.setOnMouseClicked(ActionEvent -> {
            root.getChildren().clear();
            Platform.runLater(new Thread(){
                public void run(){
                    driver.launchGame();
                }
            });
        });

        Button mainMenuButton = new Button("Back to menu");
        mainMenuButton.setOnMouseClicked(ActionEvent -> {
            root.getChildren().clear();
            Platform.runLater(new Thread(){
                public void run(){
                    driver.launchMenu();
                }
            });
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
