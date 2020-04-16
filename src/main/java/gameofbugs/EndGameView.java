package gameofbugs;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class EndGameView {
    private HBox root;
    private Color winner;

    public EndGameView(HBox root, Color winner) {
        this.root = root;
        this.winner = winner;
    }

    public void displayGameEnd() {
        Label label = new Label(winner.toString() + " wins!");

        root.getChildren().clear();
        root.getChildren().add(label);
        root.setAlignment(Pos.CENTER);
    }
}
