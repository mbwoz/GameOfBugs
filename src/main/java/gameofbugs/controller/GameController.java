package gameofbugs.controller;

import gameofbugs.model.GameModel;
import gameofbugs.model.Position;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class GameController {
    private GameModel gameModel;

    public GameController(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public void triggerBoardAction(Position pos, MouseEvent event) {
        if(event.getButton() == MouseButton.PRIMARY)
            gameModel.takeAction(pos);
        gameModel.showStack(pos);
    }

}
