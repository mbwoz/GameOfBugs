package gameofbugs;

public class GameOfBugsApplication {

    public static void main(String[] args) {
        GameView gameView = new GameView();

        GameModel gameModel = new GameModel(gameView);

        new GameController(gameView, gameModel);
    }
}
