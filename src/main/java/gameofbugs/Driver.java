package gameofbugs;

import javafx.scene.layout.HBox;


public class Driver {
    private HBox root;
    private SceneController sceneController;

    public Driver(HBox rootStart) {
        this.root = rootStart;
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
        GameController gameController = new GameController(gameModel, this);
        gameView.addController(gameController, sceneController);

        gameModel.updateBoardState();
    }

    public void launchGameEnd(Color winner) {
        EndGameView endGameView = new EndGameView(root, winner, sceneController);
        endGameView.displayGameEnd();
    }
}
