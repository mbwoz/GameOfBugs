package gameofbugs.controller;

import gameofbugs.Driver;
import gameofbugs.model.Color;
import gameofbugs.model.GameModel;
import gameofbugs.view.GameView;
import javafx.application.Platform;

public class SceneController {

    private Driver driver;

    public SceneController(Driver driver) {
        this.driver = driver;
    }

    public void triggerMenu() {
        Platform.runLater(new Thread(() -> driver.launchMenu()));
    }

    public void triggerInstruction() {
        Platform.runLater(new Thread(() -> driver.launchInstruction()));
    }

    public void triggerGameStart() {
        Platform.runLater(new Thread(() -> driver.launchGame()));
    }

    public void triggerGameEnd(Color winner) {
        Platform.runLater(new Thread(() -> driver.launchGameEnd(winner)));
    }

    public void triggerSettings() {
        Platform.runLater(new Thread(() -> driver.launchSettings()));
    }

    public void triggerResumeGame(GameModel gameModel, GameView gameView) {
        Platform.runLater(new Thread(() -> driver.launchResumeGame(gameModel, gameView)));
    }

    public void triggerPauseMenu(GameModel gameModel, GameView gameView) {
        Platform.runLater(new Thread(() -> driver.launchPauseMenu(gameModel, gameView)));
    }
}
