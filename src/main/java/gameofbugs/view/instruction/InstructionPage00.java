package gameofbugs.view.instruction;

import gameofbugs.model.Position;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class InstructionPage00 extends InstructionView {
    public InstructionPage00(HBox root) { super(root); }

    protected VBox setRightBox() {
        VBox controlArea = new VBox();
        Button nextPageButton = new Button("Next page");
        nextPageButton.setOnMouseClicked(event -> instructionSceneController.triggerPage(1));

        Button backToMenuButton = new Button("Back to menu");
        backToMenuButton.setOnMouseClicked(event -> instructionSceneController.triggerMenu());

        controlArea.getChildren().addAll(textPane, nextPageButton, backToMenuButton);

        return controlArea;
    }

    protected void drawText () {
        Text text = new Text();
        text.setText("What is GameOfBugs and how to win?\n" +
                "\n" +
                "GameOfBugs is a two player turn-based game. During the game Player controls hive of different kinds of bugs (our beloved Satori status). The game goal is to totally surround opponent`s OK. Once it is surrounded, game ends.\n" +
                "\n" +
                "Look at the Board. Black player`s OK is surrounded so White Player wins!\n");
        text.setFont(new Font(24));
        text.setWrappingWidth(400);
        textPane.setContent(text);
    }

    protected void setBoard() {
        instructionController.triggerAction(new Position(-1, 1));
        instructionController.triggerAction(new Position(16, 16));
        instructionController.triggerAction(new Position(-2, 0));
        instructionController.triggerAction(new Position(15, 17));
        instructionController.triggerAction(new Position(-1, 3));
        instructionController.triggerAction(new Position(16, 15));
        instructionController.triggerAction(new Position(-2, 2));
        instructionController.triggerAction(new Position(14, 17));
        instructionController.triggerAction(new Position(-1, 1));
        instructionController.triggerAction(new Position(15, 15));
        instructionController.triggerAction(new Position(-2, 1));
        instructionController.triggerAction(new Position(14, 18));
        instructionController.triggerAction(new Position(-1, 0));
        instructionController.triggerAction(new Position(17, 16));
        instructionController.triggerAction(new Position(-2, 1));
        instructionController.triggerAction(new Position(13, 19));
        instructionController.triggerAction(new Position(15, 15));
        instructionController.triggerAction(new Position(15, 18));
        instructionController.triggerAction(new Position(13, 19));
        instructionController.triggerAction(new Position(16, 17));
        instructionController.triggerAction(new Position(-1, 1));
        instructionController.triggerAction(new Position(18, 16));
        instructionController.triggerAction(new Position(-2, 4));
        instructionController.triggerAction(new Position(14, 16));
        instructionController.triggerAction(new Position(18, 16));
        instructionController.triggerAction(new Position(16, 14));
        instructionController.triggerAction(new Position(14, 16));
        instructionController.triggerAction(new Position(13, 17));
        instructionController.triggerAction(new Position(16, 14));
        instructionController.triggerAction(new Position(15, 16));
    }

}
