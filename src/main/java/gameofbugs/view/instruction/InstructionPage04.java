package gameofbugs.view.instruction;

import gameofbugs.model.Position;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


public class InstructionPage04 extends InstructionView {
    public InstructionPage04(HBox root) {
        super(root, 4);
    }


    @Override
    protected void setInstructionText() {
        instructionText.setText(
                "During placing or moving you have to remember about three important rules.\n\n" +
                        "Rule no. 1: You have to place your OK in 4. turn at the latest.\n\n" +
                        "Rule no. 2: You cannot move any of your pieces before placing the OK.\n\n" +
                        "Rule no. 3: If a piece you want to move is the only connection between two parts of Hive, you cannot move it.\n" +
                        "\n" +
                        "Try to move or place a piece. It`s not possible, because this is the last turn to place OK\n");
    }

    @Override
    protected void setButtons() {
        nextButton.setOnMouseClicked(event -> instructionSceneController.triggerPage(5));
        backButton.setOnMouseClicked(event -> instructionSceneController.triggerMenu());
        prevButton.setOnMouseClicked(event -> instructionSceneController.triggerPage(3));
    }

    @Override
    protected Label setTopLabel(){
        Label text = new Label("Important Rules");
        return text;
    }

    @Override
    protected void setBoard() {
        instructionController.triggerAction(new Position(-1, 1));
        instructionController.triggerAction(new Position(16, 16));
        instructionController.triggerAction(new Position(-2, 1));
        instructionController.triggerAction(new Position(15, 17));
        instructionController.triggerAction(new Position(-1, 2));
        instructionController.triggerAction(new Position(17, 16));
        instructionController.triggerAction(new Position(-2, 2));
        instructionController.triggerAction(new Position(14, 17));
        instructionController.triggerAction(new Position(-2, 0));
        instructionController.triggerAction(new Position(-1, 1));
        instructionController.triggerAction(new Position(16, 15));
        instructionController.triggerAction(new Position(-2, 0));
        instructionController.triggerAction(new Position(14, 16));
    }
}
