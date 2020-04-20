package gameofbugs.controller;

import gameofbugs.model.GameModel;
import gameofbugs.model.Position;

public class GameController {
    private GameModel gameModel;

    public GameController(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public void triggerBoardAction(Position pos) {
        System.out.println(pos.getX() + " " + pos.getY());
        gameModel.takeAction(pos);
    }

}
