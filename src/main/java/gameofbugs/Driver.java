package gameofbugs;

import gameofbugs.controller.GameController;
import gameofbugs.controller.SceneController;
import gameofbugs.model.Color;
import gameofbugs.model.GameModel;
import gameofbugs.view.*;
import javafx.scene.layout.HBox;

public class Driver {
    private HBox root;
    private SceneController sceneController;

    public Driver(HBox root) {
        this.root = root;
        this.sceneController = new SceneController(this);
    }

    public void launchMenu() {
        MenuView menuView = new MenuView(root, sceneController);
        menuView.displayGameStart();
    }

    public void launchInstruction() {
        InstructionDriver instructionDriver = new InstructionDriver(root, sceneController);
        instructionDriver.launchPage(0);
    }

    public void launchGame() {
        GameView gameView = new GameView(root);
        GameModel gameModel = new GameModel(gameView);
        GameController gameController = new GameController(gameModel);
        gameView.addController(gameController, sceneController);

        gameModel.updateBoardState();
    }

    public void launchGameEnd(Color winner) {
        EndGameView endGameView = new EndGameView(root, winner, sceneController);
        endGameView.displayGameEnd();
    }

    public void launchSettings() {
        SettingsView settingsView = new SettingsView(root, sceneController);
        settingsView.displaySettings();
    }
}
