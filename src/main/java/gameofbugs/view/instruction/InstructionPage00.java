package gameofbugs.view.instruction;

import gameofbugs.model.Position;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


public class InstructionPage00 extends InstructionView {
    public InstructionPage00(HBox root) { super(root, 0); }

    @Override
    protected void setButtons() {
        nextButton.setOnMouseClicked(event -> instructionSceneController.triggerPage(1));
        backButton.setOnMouseClicked(event -> instructionSceneController.triggerMenu());
        prevButton.setOnMouseClicked(event -> {});
    }

    @Override
    protected void setInstructionText() {
        instructionText.setText("What is GameOfBugs and how to win?\n" +
                "\n" +
                "GameOfBugs is a two player turn-based game. During the game Player controls hive of different kinds of bugs (our beloved Satori status). The game goal is to totally surround opponent`s OK. Once it is surrounded, game ends.\n" +
                "\n" +
                "Look at the Board. Black player`s OK is surrounded so White Player wins!\n");
    }

    @Override
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

    @Override
    protected Label setTopLabel() {
        Label text = new Label("Introduction");
        return text;
    }


}
