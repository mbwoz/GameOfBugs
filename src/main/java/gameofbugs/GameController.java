package gameofbugs;

public class GameController {
    private Driver driver;
    private GameModel gameModel;

    public GameController(GameModel gameModel, Driver driver) {
        this.gameModel = gameModel;
        this.driver = driver;
    }

    public void triggerBoardAction(Position pos) {
        System.out.println(pos.getX() + " " + pos.getY());
        gameModel.takeAction(pos);
    }

    public void triggerGameEnd(Color winner) {
        driver.launchGameEnd(winner);
    }

}
