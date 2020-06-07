package gameofbugs.view.instruction;

import gameofbugs.model.Position;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class InstructionPage05 extends InstructionView {
    public InstructionPage05(HBox root) {
        super(root, 5);
    }

    @Override
    protected void setInstructionText() {
        instructionText.setText(
                "OK is your most important piece. Because of that, it doesn`t move really well. OK can move only one space per turn.\n" +
                        "\n" +
                        "Try to move your OK!\n");
    }

    @Override
    protected void setButtons() {
        nextButton.setOnMouseClicked(event -> instructionSceneController.triggerPage(6));
        backButton.setOnMouseClicked(event -> instructionSceneController.triggerMenu());
        prevButton.setOnMouseClicked(event -> instructionSceneController.triggerPage(4));
    }

    @Override
    protected Label setTopLabel(){
        Label text = new Label("OK Status");
        return text;
    }

    @Override
    protected void setBoard() {
        instructionController.triggerAction(new Position(-1, 1));
        instructionController.triggerAction(new Position(16, 16));
        instructionController.triggerAction(new Position(-2, 1));
        instructionController.triggerAction(new Position(15, 17));
        instructionController.triggerAction(new Position(-1, 0));
        instructionController.triggerAction(new Position(16, 15));
        instructionController.triggerAction(new Position(-2, 2));
        instructionController.triggerAction(new Position(15, 18));
        instructionController.triggerAction(new Position(-1, 1));
        instructionController.triggerAction(new Position(17, 16));
        instructionController.triggerAction(new Position(-2, 0));
        instructionController.triggerAction(new Position(14, 17));
        instructionController.triggerAction(new Position(-1, 1));
        instructionController.triggerAction(new Position(17, 15));
        instructionController.triggerAction(new Position(-2, 2));
        instructionController.triggerAction(new Position(14, 16));
        instructionController.triggerAction(new Position(17, 15));
        instructionController.triggerAction(new Position(16, 17));
        instructionController.triggerAction(new Position(14, 16));
        instructionController.triggerAction(new Position(14, 18));
        instructionController.triggerAction(new Position(-2, 1));
        instructionController.triggerAction(new Position(-2, 2));
        instructionController.triggerAction(new Position(-1, 3));
        instructionController.triggerAction(new Position(17, 17));
        instructionController.triggerAction(new Position(-2, 1));
        instructionController.triggerAction(new Position(14, 16));
    }
}
