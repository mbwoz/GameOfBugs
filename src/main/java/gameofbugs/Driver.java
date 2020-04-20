package gameofbugs;

import gameofbugs.controller.GameController;
import gameofbugs.controller.SceneController;
import gameofbugs.model.Color;
import gameofbugs.model.GameModel;
import gameofbugs.view.EndGameView;
import gameofbugs.view.GameView;
import gameofbugs.view.InstructionView;
import gameofbugs.view.MenuView;
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
        InstructionView instructionView = new InstructionView(root, sceneController);
        instructionView.displayInstruction();
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
}
