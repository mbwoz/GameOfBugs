package gameofbugs;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class GameController {
    private GameModel gameModel;

    public GameController(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public void tileClickedEvent(Position pos) {
        System.out.println(pos.getX() + " " + pos.getY());
        gameModel.takeAction(pos);
    }
}
