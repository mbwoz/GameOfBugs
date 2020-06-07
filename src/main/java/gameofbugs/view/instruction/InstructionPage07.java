package gameofbugs.view.instruction;

import gameofbugs.model.Position;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


public class InstructionPage07 extends InstructionView {
    public InstructionPage07(HBox root) {
        super(root);
    }

    @Override
    protected void setInstructionText() {
        instructionText.setText(
                "RTE jumps from its position over any – but at least one! – number of pieces to the closest free place in a straight line. \n" +
                        "\n" +
                        "Check how your RTE jumps!\n");
    }

    @Override
    protected void setButtons() {
        nextButton.setOnMouseClicked(event -> instructionSceneController.triggerPage(8));
        backButton.setOnMouseClicked(event -> instructionSceneController.triggerMenu());
        prevButton.setOnMouseClicked(event -> instructionSceneController.triggerPage(6));
    }

    @Override
    protected Label setTopLabel(){
        Label text = new Label("RTE Status");
        return text;
    }

    @Override
    protected void setBoard() {
        instructionController.triggerAction(new Position(-1, 0));
        instructionController.triggerAction(new Position(16, 16));
        instructionController.triggerAction(new Position(-2, 0));
        instructionController.triggerAction(new Position(15, 17));
        instructionController.triggerAction(new Position(-1, 1));
        instructionController.triggerAction(new Position(16, 15));
        instructionController.triggerAction(new Position(-2, 1));
        instructionController.triggerAction(new Position(15, 18));
        instructionController.triggerAction(new Position(-2, 3));
        instructionController.triggerAction(new Position(-1, 1));
        instructionController.triggerAction(new Position(17, 16));
        instructionController.triggerAction(new Position(-2, 4));
        instructionController.triggerAction(new Position(14, 17));
        instructionController.triggerAction(new Position(-1, 2));
        instructionController.triggerAction(new Position(17, 15));
        instructionController.triggerAction(new Position(-2, 1));
        instructionController.triggerAction(new Position(16, 18));
        instructionController.triggerAction(new Position(-1, 2));
        instructionController.triggerAction(new Position(15, 15));
        instructionController.triggerAction(new Position(16, 18));
        instructionController.triggerAction(new Position(14, 16));
        instructionController.triggerAction(new Position(-1, 2));
        instructionController.triggerAction(new Position(17, 17));
        instructionController.triggerAction(new Position(-2, 2));
        instructionController.triggerAction(new Position(13, 17));
        instructionController.triggerAction(new Position(17, 17));
        instructionController.triggerAction(new Position(17, 14));
        instructionController.triggerAction(new Position(15, 18));
        instructionController.triggerAction(new Position(16, 14));
        instructionController.triggerAction(new Position(15, 17));
        instructionController.triggerAction(new Position(15, 15));
        instructionController.triggerAction(new Position(18, 15));
        instructionController.triggerAction(new Position(15, 17));
        instructionController.triggerAction(new Position(17, 14));
        instructionController.triggerAction(new Position(16, 15));
        instructionController.triggerAction(new Position(13, 17));
        instructionController.triggerAction(new Position(15, 15));
        instructionController.triggerAction(new Position(15, 17));
        instructionController.triggerAction(new Position(18, 15));
        instructionController.triggerAction(new Position(16, 17));
        instructionController.triggerAction(new Position(15, 17));
        instructionController.triggerAction(new Position(14, 18));
        instructionController.triggerAction(new Position(16, 17));
        instructionController.triggerAction(new Position(16, 13));
        instructionController.triggerAction(new Position(17, 16));
        instructionController.triggerAction(new Position(-1, 3));
    }
}
