package gameofbugs;

import javafx.scene.layout.HBox;

public class Driver {
    private HBox root;

    public Driver(HBox root) {
        this.root = root;
    }

    public void launchGame() {
        GameView gameView = new GameView(root);
        GameModel gameModel = new GameModel(gameView);
        GameController gameController = new GameController(gameModel, this);
        gameView.addController(gameController);

        gameModel.updateBoardState();
    }

    public void launchGameEnd(Color winner) {
        EndGameView endGameView = new EndGameView(root, winner);

        endGameView.displayGameEnd();
    }
}
